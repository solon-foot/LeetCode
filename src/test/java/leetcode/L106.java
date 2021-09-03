package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class L106 {
    private interface Solution {
        TreeNode buildTree(int[] inorder, int[] postorder);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(TreeNode.create(3,9,20,null,null,15,7), solution.buildTree(new int[]{9,3,15,20,7},new int[]{9,15,7,20,3}));
    }

    private static class Solution1 implements Solution {

        @Override
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
        }

        public TreeNode helper(int[] inorder, int[] postorder, int index, int inStart, int inEnd) {
            if (inStart > inEnd) {
                return null;
            }

            int currentVal = postorder[index];
            TreeNode current = new TreeNode(currentVal);

            int inIndex = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == currentVal) {
                    inIndex = i;
                }
            }
            TreeNode left = helper(inorder, postorder, index - (inEnd- inIndex) - 1, inStart, inIndex - 1);
            TreeNode right = helper(inorder, postorder, index - 1, inIndex + 1, inEnd);
            current.left = left;
            current.right = right;
            return current;
        }

        void left(TreeNode root){
            if (root == null)return;
            System.out.print(root.val+" ");
            left(root.left);
            left(root.right);
        }
        void mid(TreeNode root){
            if (root == null)return;
            mid(root.left);
            System.out.print(root.val+" ");
            mid(root.right);
        }
        void right(TreeNode root){
            if (root == null)return;
            right(root.left);
            right(root.right);
            System.out.print(root.val+" ");
        }
    }
    private static class Solution2 implements Solution {
        int in,post;//中序和倒序序列的索引
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            in=inorder.length-1;
            post=postorder.length-1;
            return recursive(inorder, postorder, Integer.MAX_VALUE);
        }
        public TreeNode recursive(int[] inorder,int[] postorder,int stop){
            if(post<0)return null;
            if(inorder[in]==stop){
                in--;
                return null;
            }
            int curVal=postorder[post--];
            TreeNode cur=new TreeNode(curVal);
            //注意顺序，倒着的后序遍历是先走右节点的先序遍历
            cur.right=recursive(inorder, postorder, curVal);
            //当先序遍历（倒）开始返回时，中序遍历（倒）开始记录节点，也就是该节点没有右节点
            cur.left=recursive(inorder, postorder, stop);
            //当中序遍历(倒)下一个节点为该节点父节点时，该节点没有左节点
            return cur;
        }
    }
}