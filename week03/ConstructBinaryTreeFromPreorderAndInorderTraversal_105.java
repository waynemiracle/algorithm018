package week03;

/*
105. 从前序与中序遍历序列构造二叉树
https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {


    /*
    preorder：[根结点，[左子树的前序遍历],[右子树的前序遍历]]
    inorder：[[左子树的前序遍历],根结点，[右子树的前序遍历]]
    那么preorder中左子树结点个数 与 inorder中左子树结点个数 相等
    前序遍历，定位根结点，然后对照中序遍历划分左右子树，
     */
    //时间复杂O(n^2)、
    // 空间复杂度O(1)这里不计算递归方法占用的空间
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0
                || inorder == null || inorder.length == 0) {
            return null;
        }
        return helpBuildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode helpBuildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        //递归终止条件
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        //建立根结点
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = 0;//找到根结点在中序中的位置
        while (inorder[inStart + index] != preorder[preStart]) {
            index++;
        }
        //构建左子树
        root.left = helpBuildTree(preorder,preStart + 1, preStart + index, inorder, inStart, inStart + index - 1);
        //构建右子树
        root.right = helpBuildTree(preorder,preStart + index + 1, preEnd, inorder, inStart + index + 1, inEnd);
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
