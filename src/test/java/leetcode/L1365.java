package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 1365. 有多少小于当前数字的数字
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * <p>
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class L1365 {
    private interface Solution {
        int[] smallerNumbersThanCurrent(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertArrayEquals(new int[]{4, 0, 1, 1, 3}, solution.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3}));
        assertArrayEquals(new int[]{2, 1, 0, 3}, solution.smallerNumbersThanCurrent(new int[]{6, 5, 4, 8}));
        assertArrayEquals(new int[]{0, 0, 0, 0}, solution.smallerNumbersThanCurrent(new int[]{7, 7, 7, 7}));
    }

    private static class Solution1 implements Solution {

        @Override
        public int[] smallerNumbersThanCurrent(int[] nums) {

            int[] res = new int[101];
            for (int num : nums) {
                res[num]++;
            }
            for (int i = 1; i < res.length; i++) {
                res[i] += res[i - 1];
            }
            int[] ans = new int[nums.length];
            for (int i = 0; i < ans.length; i++) {
                if (nums[i] == 0) ans[i] = 0;
                else ans[i] = res[nums[i] - 1];
            }
            return ans;
        }
    }
}