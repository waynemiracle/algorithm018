package week02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
429. N叉树的层序遍历
https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/


说明:

    树的深度不会超过 1000。
    树的节点总数不会超过 5000。

 */

public class NAryTreeLevelOrderTraversal_429 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                level.add(cur.val);
                for (Node child : cur.children) {
                    if (child != null) {
                        queue.offer(child);
                    }
                }
            }
            res.add(level);
        }
        return res;
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
    }
}
