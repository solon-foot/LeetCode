package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class L144 {
    private interface Solution {
        List<Integer> preorderTraversal(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Arrays.asList(1,2,3), solution.preorderTraversal(TreeNode.create(1,null,2,3)));
        assertEquals(Arrays.asList(1,2,4,5,3,6), solution.preorderTraversal(TreeNode.create(1,2,3,4,5,6)));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            if (root == null)return ans;
            Stack<TreeNode> list = new Stack<>();
            list.add(root);
            while (!list.isEmpty()){
                TreeNode node = list.pop();
                ans.add(node.val);
                if (node.right!=null) list.add(node.right);
                while (node.left!=null){
                    node = node.left;
                    ans.add(node.val);
                    if (node.right!=null)
                        list.add(node.right);
                }
            }
            return ans;
        }
    }
}