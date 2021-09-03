package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class L94 {
    private interface Solution {
        List<Integer> inorderTraversal(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
//        assertEquals(Arrays.asList(1, 3, 2), solution.inorderTraversal(TreeNode.create(1, null, 2, 3)));
        assertEquals(Arrays.asList(8, 4, 9, 2, 5, 1, 6, 3, 7), solution.inorderTraversal(TreeNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                ans.add(node.val);
                node = node.right;

            }
            return ans;
        }
    }


    private static class Solution2 implements Solution {

        @Override
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            dfs(root, ans);
            return ans;
        }

        void dfs(TreeNode root, List<Integer> ans) {
            if (root == null) return;
            dfs(root.left, ans);
            ans.add(root.val);
            dfs(root.right, ans);
        }
    }
}