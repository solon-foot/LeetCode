package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 327. 区间和的个数
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 *
 * 示例:
 *
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 */
public class L327 {
    private interface Solution {
        int countRangeSum(int[] nums, int lower, int upper);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
//        assertEquals(3, solution.countRangeSum(new int[]{-2,5,-1},-2,2));
        assertEquals(3, solution.countRangeSum(new int[]{-2147483647,0,-2147483647,2147483647},-564,3864));
    }

    private static class Solution1 implements Solution {

        @Override
        public int countRangeSum(int[] nums, int lower, int upper) {
            if (nums.length==0)return 0;
            long[] data = new long[nums.length];
            long last = 0;
            for (int i = 0; i < nums.length; i++) {
                data[i] = last += nums[i];
            }
            int count = 0;
            for (int i = 0; i < data.length; i++) {
                long t = 0;
                if (i>0) t = data[i-1];
                for (int j = i; j < nums.length; j++) {
                    long t2 = data[j]-t;
                    if (t2>=lower && t2<=upper){

                        count++;
                    }
                }
            }
            return count;
        }
    }
}