package leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * <p>
 * 输出: false
 * <p>
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class L416 {
    private interface Solution {
        boolean canPartition(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, solution.canPartition(new int[]{1, 5, 11, 5}));
        assertEquals(false, solution.canPartition(new int[]{1, 2, 3, 5}));
        assertEquals(false, solution.canPartition(new int[]{ 100, 100, 99, 97}));
        assertEquals(true, solution.canPartition(new int[]{ 100, 100, 100, 300}));
        assertEquals(true, solution.canPartition(new int[]{ 100, 100, 100,100,100, 300}));
        assertEquals(true, solution.canPartition(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,97,95}));
        assertEquals(true, solution.canPartition(new int[]{1,1,1,1,1,1,97,95}));
        assertEquals(false, solution.canPartition(new int[]{4,4,4,4,4,4,4,4,8,8,8,8,8,8,8,8,12,12,12,12,12,12,12,12,16,16,16,16,16,16,16,16,20,20,20,20,20,20,20,20,24,24,24,24,24,24,24,24,28,28,28,28,28,28,28,28,32,32,32,32,32,32,32,32,36,36,36,36,36,36,36,36,40,40,40,40,40,40,40,40,44,44,44,44,44,44,44,44,48,48,48,48,48,48,48,48,52,52,52,52,52,52,52,52,56,56,56,56,56,56,56,56,60,60,60,60,60,60,60,60,64,64,64,64,64,64,64,64,68,68,68,68,68,68,68,68,72,72,72,72,72,72,72,72,76,76,76,76,76,76,76,76,80,80,80,80,80,80,80,80,84,84,84,84,84,84,84,84,88,88,88,88,88,88,88,88,92,92,92,92,92,92,92,92,96,96,96,96,96,96,96,96,97,99}));
        assertEquals(false, solution.canPartition(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97}));
    }

    private static class Solution1 implements Solution {

        @Override
        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return false;
            }
            int sum = 0, maxNum = 0;
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            if ((sum &1)  != 0) {
                return false;
            }
            int target = sum>>1;
            if (maxNum > target) {
                return false;
            }
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            for (int num : nums) {
                for (int j = target; j >= num; --j) {
                    dp[j] |= dp[j - num];
                    if (dp[target])return true;
                }
            }
            return dp[target];
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public boolean canPartition(int[] nums) {
            if(nums == null || nums.length < 2) return false;
            int sum = 0;
            for (int num : nums){
                sum += num;
            }
            if (sum % 2 != 0) return false;
            int target = sum/2;
            return dfs(nums, target, 0);
        }
        public boolean dfs(int[] nums, int target, int index){
            if (target == 0) return true;
            if (target < 0) return false;
            for (int i = index; i < nums.length; i++){
                if (i > index && nums[i] == nums[i - 1]) continue;
                if (dfs(nums, target - nums[i], i + 1)) return true;
            }
            return false;
        }
    }
}