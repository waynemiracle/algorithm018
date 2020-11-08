package week03;

/*
236. 二叉树的最近公共祖先
https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */

public class LowestCommonAncestorOfABinaryTree_236 {

    /*
    递归
    从左子树或者右子树中有没有p、q中的任意一个元素，如果其中一个有了就向上返回根结点
    1、左子树查找有没有p、q中的任意一个元素
    2、右子树查找有没有p、q中的任意一个元素
    3、返回值的情况
        当root为null，肯定要返回null即root
        当root等于p、q中任意一个元素，表明这个分支有用，返回root
        判断左边或者右边返回值情况
            (1) left、right都不为null，则root可以直接返回
            (2) 其中一个非空返回非空那一支。p、q都在这一支非空上面
            (3) 两个都为空返回空吧，把这个分支放弃

        时间复杂度O(n)，n为二叉树结点数，需要递归遍历所有结点
        空间复杂度O(n)，最差情况下递归深度达到n，系统需要使用O(n)大小的额外空间
     */
    public TreeNode lowestCommonAncestor_01(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor_01(root.left, p, q);
        TreeNode right = lowestCommonAncestor_01(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left == null && right == null) {
            return null;
        }

        return left == null ? right : left;

    }
    /*简化*/
    public TreeNode lowestCommonAncestor_02(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor_02(root.left, p, q);
        TreeNode right = lowestCommonAncestor_02(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }



    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
   }
}
