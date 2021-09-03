package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L75 {
    private interface Solution {
        void sortColors(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        int[] nums = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        TLog.e(nums);
        assertEquals(true, true);
    }

    private static class Solution1 implements Solution {

        @Override
        public void sortColors(int[] nums) {
            int n = nums.length;
            int p0 = 0, p2 = n - 1;
            for (int i = 0; i <= p2; ++i) {
                while (i <= p2 && nums[i] == 2) {
                    int temp = nums[i];
                    nums[i] = nums[p2];
                    nums[p2] = temp;
                    --p2;
                }
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[p0];
                    nums[p0] = temp;
                    ++p0;
                }
            }

        }
    }
}