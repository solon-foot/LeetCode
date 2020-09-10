package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 107. 二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class L107 {
    private interface Solution {
        List<List<Integer>> levelOrderBottom(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Arrays.asList(Arrays.asList(15,7),Arrays.asList(9,20),Arrays.asList(3)), solution.levelOrderBottom(TreeNode.create(3,9,20,null,null,15,7)));
    }

    private static class Solution1 implements Solution {


        @Override
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            LinkedList<List<Integer>> ans = new LinkedList<>();
            if (root==null)return ans;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int size;
            while ((size = queue.size())!=0){
                List<Integer> arr = new ArrayList<>(size);
                ans.addFirst(arr);
                while (size-->0){
                    TreeNode poll = queue.poll();
                    arr.add(poll.val);

                    if (poll.left!=null){
                        queue.offer(poll.left);
                    }
                    if (poll.right!=null)
                        queue.offer(poll.right);
                }
            }

            return ans;
        }
    }
}