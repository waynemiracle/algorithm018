# HashMap

JDK8中HashMap的结构是数组+链表+红黑树的复合结构。

HashMap默认大小是16，加载因子0.75，可以根据项目需要自定义加载因子。

HashMap的扩容操作是比较消耗时间的，如果可以的话最好预估HashMap初始化的容量，以此来避免频繁的扩容操作。

HashMap中链表转化为红黑树要满足两个条件才行(1)链表的长度已经达到8个了，(2)HashMap容量大于64即可。

HashMap中索引的定位和元素的查找，非常依赖key的hashCode和equal方法，我们在自定义的类型的时候需要好好考虑如何比较两个对象即重写hashCode与equals方法



不是通过构造函数初始化，而是在插入时通过扩容初始化，有效防止了初始化HashMap没有数据插入造成空间浪费可能造成内存泄露的情况，第一初始化table时,resize()中对threshold值进行了重新计算= capacity*loadFactor

tableSizeFor(initialCapacity)这个方法，返回一个 大于等于 initialCapacity 最小的2的幂次方



## 常量 、实例变量

```
//默认hash表的长度16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

//has表最大容量是2的30次幂
static final int MAXIMUM_CAPACITY = 1 << 30;

//默认装载因子 0.75
static final float DEFAULT_LOAD_FACTOR = 0.75f;

//链表的数量 大于等于 8并且 桶的数量大于等于64时链表树化
static final int TREEIFY_THRESHOLD = 8;

//hash表某个节点链表数量 小于等于6 时树转变为链表
static final int UNTREEIFY_THRESHOLD = 6;

//树化时最小桶的数量
static final int MIN_TREEIFY_CAPACITY = 64;
```

实例变量

```
//hash表
transient Node<K,V>[] table;

//元素的数量
transient int size;

//HashMap结构修改的次数
transient int modCount;

//扩容的阈值，
int threshold;

final float loadFactor;
```

## 构造函数



hash表没有在构造函数中初始化，而是第一次在存储key-value时进行初始化，



主要看一下tableSizeFor(initialCapacity)这个方法

```
this.threshold = tableSizeFor(initialCapacity);
```

将你传入的initialCapacity做计算，返回一个 大于等于 initialCapacity 最小的2的幂次方。

```
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```

## put()

//第一初始化table时,resize()中对threshold值进行了重新计算= capacity*loadFactor

```
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```

```
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //当table为空时初始化table，不是通过构造函数初始化，而是在插入时通过扩容初始化，
    //有效防止了初始化HashMap没有数据插入造成空间浪费可能造成内存泄露的情况
    //第一初始化table时,resize()中对threshold值进行了重新计算= capacity*loadFactor
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
     //存放新键值对
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        //旧键值对的覆盖 并且第一就是的
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p; 
            //在红黑树中查找旧键值对更新
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
        ////将新键值对放在链表的最后
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    //当链表的长度 大于等于 转化为树的阈值（8），并且hash桶的长度大于等于							//MIN_TREEIFY_CAPACITY(64)，链表转化为红黑树
                    //一开始p指向table中的即第一个结点
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                //链表中包含键值对
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        //map中含有旧key，返回旧值
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue; 添加的是旧key就返回了
        }
    }
    //map调整此时加一
    ++modCount;
    //添加的是一个新的键值对，（添加后）键值对的数量达到阈值需要扩容
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

## 扩容resize()

（1）初始化HashMap时，第一次进行put操作

（2）当键值对的个数大于threshold阀值时产生扩容，threshold=size*loadFactor

```
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    //如果旧的hash桶不为空
    if (oldCap > 0) {
    //超过hash桶的最大长度，将阈值设为最大值
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        //新的hash桶的长度2倍扩容没有超过最大长度，将新容量阈值扩容为以前的2倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    //如果hash表阈值已经初始化过
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
    //如果旧hash桶，并且hash桶容量阈值没有初始化，那么需要初始化新的hash桶的容量和新容量阈值
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    //新的阈值赋值
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    //为当前容量阈值赋值
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    //初始化hash桶
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    //如果旧的hash桶不为空，需要将旧的hash表里的键值对重新映射到新的hash桶中
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                //只有一个节点，通过索引位置直接映射
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                    //如果是红黑树，需要进行树拆分然后映射
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                //如果是多个节点的链表，将原链表拆分为两个链表，两个链表的索引位置，
                //一个为原索引，一个为原索引加上旧Hash桶长度的偏移量
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        //链表1
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        //链表2
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    ////链表1存于原索引
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    //链表2存于原索引加上原hash桶长度的偏移量
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

## get()

```
public V get(Object key) {
    Node<K,V> e;
    //如果该节点不为空，返回值
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

/**
 * Implements Map.get and related methods
 *
 * @param hash hash for key
 * @param key the key
 * @return the node, or null if none
 */
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    //table不为空,根据Key的hash计算出在数组中对应的索引index
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        //判断第一个节点是否为目标Key，如果是立即返回
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
        	//红黑树时，从getTreeNode中获取对应key的节点，并返回
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
               
            do {
             //不是红黑树，就遍历链表，获取对应key的节点，并返回
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```




