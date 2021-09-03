package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class L98 {
    private interface Solution {
        boolean isValidBST(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, solution.isValidBST(TreeNode.create(2,1,3)));
        assertEquals(true, solution.isValidBST(TreeNode.create(2,Integer.MIN_VALUE,3)));
        assertEquals(true, solution.isValidBST(TreeNode.create(2,Integer.MIN_VALUE,3)));
//        assertEquals(false, solution.isValidBST(TreeNode.create(5,1,4,null,null,3,6)));
//        TreeNode node = TreeNode.create(5,2,6,1,4,null,7,null,null,3);
//        assertEquals(false, solution.isValidBST(node));
//        node = TreeNode.create(2,1,3);
//        System.out.println(node.prettyString());
//        assertEquals(false, solution.isValidBST(node));
//
//        List<Integer> ans = new ArrayList<>();
//        ((Solution1) solution).bst(node,ans);
//        System.out.println(ans);
    }

    private static class Solution1 implements Solution {

        Integer t=null;
        @Override
        public boolean isValidBST(TreeNode root) {
            if (root == null)return true;
            if (!isValidBST(root.left)) {
                return false;
            }
            if (t!=null&&t>=root.val){
                return false;
            }
            t = root.val;
            return isValidBST(root.right);
        }

        void bst(TreeNode root, List<Integer> ans){
            if (root == null)return;
            bst(root.left,ans);
            ans.add(root.val);
            bst(root.right,ans);
        }
    }

}