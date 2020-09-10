package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class L40 {
    private interface Solution {
        List<List<Integer>> combinationSum2(int[] candidates, int target);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
//        assertEquals(true, solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
//        assertEquals(true, solution.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
        assertEquals(true,solution.combinationSum2(new int[]{1,2,4,1,2,4,1,2,4,1,2,4,1,2,4,1,2,4,1,2,4,1,2,4},8));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>(), ans);
            return ans;
        }

        void dfs(int[] candidates, int target, int i, ArrayList<Integer> temp, List<List<Integer>> ans) {
            if (target == 0) {
                ans.add(new ArrayList<>(temp));
                return;
            }
            for (int j = i; j < candidates.length; j++) {
                if (j>i&&candidates[j-1]==candidates[j])continue;
                if (candidates[j]>target)break;
                temp.add(candidates[j]);
                dfs(candidates,target-candidates[j],j+1,temp,ans);
                temp.remove(temp.size()-1);
            }
        }
    }
}