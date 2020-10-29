package week02;
/*
589. N叉树的前序遍历
https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/


说明: 递归法很简单，你可以使用迭代法完成此题吗?
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NAryTreePreOrderTraversal_589 {

    //递归
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    private void preorderTraversal(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.children == null) return;
        for (Node child : root.children) {
            preorderTraversal(child,list);
        }
    }

    //迭代
    public List<Integer> preorder_02(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            list.add(cur.val);
            List<Node> children = cur.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }

        return list;
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
