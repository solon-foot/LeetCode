package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class L113 {
    private interface Solution {
        List<List<Integer>> pathSum(TreeNode root, int sum);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, solution.pathSum(TreeNode.create(5,4,8,11,null,13,4,7,2,null,null,5,1),22));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> ans = new ArrayList<>();
            dfs(root,sum,new ArrayList<>(),ans);
            return ans;
        }
        void dfs(TreeNode root,int sum,List<Integer> list,List<List<Integer>> ans){
            if (root == null)return;
            list.add(root.val);
            sum-=root.val;
            if (root.left == null && root.right == null){
                if (sum ==0) ans.add(new ArrayList<>(list));
            } else {
                dfs(root.left,sum,list,ans);
                dfs(root.right,sum,list,ans);
            }
            list.remove(list.size()-1);
        }
    }
}