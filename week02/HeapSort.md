# HeapSort

https://www.geeksforgeeks.org/heap-sort/

堆排序是指利用堆这种数据结构而设计的一种排序算法，堆排序是一种**选择排序**(简单选择排序、树形选择排序、堆排序)，

它最好、最坏、平均时间复杂度均为O(nlogn)，它是不稳定排序。



二叉堆

完全二叉树是一个棵二叉树，其中除最后一层外每一层都完全填满，并且所有节点都尽可能的靠左。

二叉堆是一个完全二叉树，其中任意节点的值 大于或者 小于其两个子节点的值，前者称为大顶堆，后者称为小顶堆。



基于数组实现的二叉堆

二叉堆是一个完全二叉树，它很容易地表示为一个数组，

(1)如果父结点存储在索引 i，左孩子的索引时 2 * i +1，右孩子的索引是 2 * i + 2。

(2)索引为i的父结点的索引是floor((i - 1)/2)



升序排列的堆排序算法：

1. 将待排序序列构成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点

2. 将其与末尾元素进行交换，此时末尾就是最大值

3. 然后将剩余n-1个元素重新构造成一个堆，就会得到n个元素的次小值，如此反复执行，便能得到一个有序序列

   

时间复杂度

heapify的时间复杂度为O（logn），createAndBuildHeap（）的时间复杂度为O（n），

堆排序的整体时间复杂度就为O（n logn）



Java代码实现

// Java program for implementation of Heap Sort 
public class HeapSort 
{ 
    public void sort(int arr[]) 
    { 
        int n = arr.length; 

        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
      
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            // Move current root to end 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
      
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
    } 
      
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(int arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
      
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
      
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
      
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
      
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 
      
    /* A utility function to print array of size n */
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    } 
      
    // Driver program 
    public static void main(String args[]) 
    { 
        int arr[] = {12, 11, 13, 5, 6, 7}; 
        int n = arr.length; 
      
        HeapSort ob = new HeapSort(); 
        ob.sort(arr); 
      
        System.out.println("Sorted array is"); 
        printArray(arr); 
    } 
} 

