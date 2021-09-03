package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class L16 {
    private interface Solution {
        int threeSumClosest(int[] nums, int target);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
//        assertEquals(2, solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        assertEquals(-2, solution.threeSumClosest(new int[]{-3,-2,-5,3,-4}, -1));
    }

    private static class Solution1 implements Solution {

        @Override
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int n = nums.length;
            int temp;
            int res = -10000;
            for (int i = 0; i < n; ++i) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int k = n - 1;

                for (int j = i + 1; j < n; ++j) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    temp = nums[i] + nums[j];
                    while (j < k && nums[k] + temp > target) --k;
                    if (k + 1 < n) {

                        int t = temp + nums[k + 1];
                        TLog.e(t);
                        if (Math.abs(target - res) > Math.abs(target - t)) {
                            res = t;
                        }
                    }
                    if (j == k) {
                        break;
                    }
                    temp += nums[k];
                    if (temp == target) return target;

                    if (Math.abs(target - res) > Math.abs(target - temp)) {
                        res = temp;
                        TLog.e(res);
                    }


                }
            }
            return res;
        }
    }
}