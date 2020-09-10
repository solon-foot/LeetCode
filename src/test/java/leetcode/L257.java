package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class L257 {
    private interface Solution {
        List binaryTreePaths(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Arrays.asList("1->2->5", "1->3"), solution.binaryTreePaths(TreeNode.create(1, 2, 3, null, 5)));
        assertEquals(Arrays.asList("1->2->4->8", "1->2->4->9", "1->2->5->10", "1->3->6", "1->3->7"), solution.binaryTreePaths(TreeNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> ans = new ArrayList<>();
            dfs(root, new StringBuilder(), ans);
            return ans;
        }

        void dfs(TreeNode root, StringBuilder sb, List<String> ans) {
            if (root==null)return;

            sb.append(root.val);
            if (root.left == null && root.right ==null) {
                ans.add(sb.toString());
                return;
            }

            sb.append("->");
            int len = sb.length();
            dfs(root.left, sb, ans);
            sb.setLength(len);
            dfs(root.right, sb, ans);
        }
    }
}