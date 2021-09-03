package leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class L78 {
    private interface Solution {
        List<List<Integer>> subsets(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(Arrays.asList(
                1
        ), solution.subsets(new int[]{1,2,3}));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<List<Integer>> subsets(int[] nums) {
            int l = nums.length;
            int len = 1<<l;
            List<List<Integer>> ans = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                List<Integer> data = new ArrayList<>();
                ans.add(data);
                int t = 1;
                for (int j = 0; j < l; j++,t<<=1) {
                    if((t&i)!=0) data.add(nums[j]);
                }

            }
            return ans;
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public List<List<Integer>> subsets(int[] nums) {
            int l = nums.length;
            int len = 1<<l;
            List<List<Integer>> ans = new ArrayList<>(len);
            dfs(0,nums,new ArrayList<Integer>(l),ans);
            return ans;
        }
        void dfs(int cur,int[] nums,List<Integer> t,List<List<Integer>> ans){
            if (cur == nums.length){
                ans.add(new ArrayList<>(t));
                return;
            }
            t.add(nums[cur]);
            dfs(cur+1,nums,t,ans);
            t.remove(t.size()-1);
            dfs(cur+1,nums,t,ans);
        }
    }
}