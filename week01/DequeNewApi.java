package week01;

import java.util.Deque;
import java.util.LinkedList;

public class DequeNewApi {

    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<String>();
        deque.addFirst("a");    // 从头加
        deque.addLast("b");     // 从尾加
        System.out.println(deque);//[a, b]
        deque.push("c");        // 从头加
        System.out.println(deque);//[c, a, b]
        deque.addFirst("d");    // 从头加
        deque.addLast("e");     // 从尾加
        deque.push("f");        // 从尾加
        System.out.println(deque);//[f, d, c, a, b, e]
    }
}
