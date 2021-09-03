package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 701. 二叉搜索树中的插入操作
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 * <p>
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 * <p>
 * <p>
 * <p>
 * 例如,
 * <p>
 * 给定二叉搜索树:
 * <p>
 * 4
 * / \
 * 2   7
 * / \
 * 1   3
 * <p>
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   /
 * 1   3 5
 * 或者这个树也是有效的:
 * <p>
 * 5
 * /   \
 * 2     7
 * / \
 * 1   3
 * \
 * 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 给定的树上的节点数介于 0 和 10^4 之间
 * 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 * -10^8 <= val <= 10^8
 * 新值和原始二叉搜索树中的任意节点值都不同
 */
public class L701 {
    private interface Solution {
        TreeNode insertIntoBST(TreeNode root, int val);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        TreeNode node = TreeNode.create(4, 2, 7, 1, 3);
//        solution.insertIntoBST(node,5);
//        TLog.e(node.midorderTraversal());
//        solution.insertIntoBST(node,6);
//        TLog.e(node.midorderTraversal());
//        solution.insertIntoBST(node,0);
//        TLog.e(node.midorderTraversal());
        solution.insertIntoBST(node, 8);
        TLog.e(node.midorderTraversal());
        assertEquals(true, true);
    }

    private static class Solution1 implements Solution {

        @Override
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            if (val > root.val) if (root.right == null) root.right = new TreeNode(val);
            else insertIntoBST(root.right, val);
            else if (root.left == null) root.left = new TreeNode(val);
            else insertIntoBST(root.left, val);
            return root;
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            if (val > root.val) root.right = insertIntoBST(root.right, val);
            else root.left = insertIntoBST(root.left, val);
            return root;
        }
    }
}