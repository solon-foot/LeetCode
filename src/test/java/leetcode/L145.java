package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class L145 {
    private interface Solution {
        List<Integer> postorderTraversal(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(Arrays.asList(3,2,1), solution.postorderTraversal(TreeNode.create(1,null,2,3)));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null)return ans;
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            ans.add(root.val);
            return ans;
        }
        List<Integer> ans = new ArrayList<>();
    }

    private static class Solution2 implements Solution {

        @Override
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.right == null || root.right == prev) {
                    res.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    stack.push(root);
                    root = root.right;
                }
            }
            return res;
        }

    }
}