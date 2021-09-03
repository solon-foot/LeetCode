package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * 输出: 6
 */
public class L222 {
    private interface Solution {
        int countNodes(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(1, solution.countNodes(TreeNode.create(1)));
        assertEquals(2, solution.countNodes(TreeNode.create(1, 2)));
        assertEquals(3, solution.countNodes(TreeNode.create(1, 2, 3)));
        assertEquals(6, solution.countNodes(TreeNode.create(1, 2, 3, 4, 5, 6)));
    }

    private static class Solution1 implements Solution {

        @Override
        public int countNodes(TreeNode root) {
            if (root == null) return 0;
            int left = 0;
            int right = 0;
            TreeNode temp = root.left;
            while (temp != null) {
                left++;
                temp = temp.left;
            }
            temp = root.right;
            while (temp != null) {
                right++;
                temp = temp.left;
            }
            return (left == right) ? countNodes(root.right) + (1 << left) : countNodes(root.left) + (1 << right);
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public int countNodes(TreeNode root) {
            if (root == null) return 0;
            int left = 0;
            int count = 0;
            TreeNode temp = root.left;
            while (temp != null) {
                left++;
                temp = temp.left;
            }
            TreeNode cursor = root;
            while (cursor != null) {
                temp = cursor.right;
                int right = 0;
                while (temp != null) {
                    right++;
                    temp = temp.left;
                }
                if (left == right) {
                    count += 1 << left;
                    left = right-1;
                    cursor = cursor.right;
                } else {
                    count += 1<<right;
                    left = left-1;
                    cursor = cursor.left;
                }
            }

            return count;
        }
    }
}