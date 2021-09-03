package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 *
 */
public class L31 {
    private interface Solution {
        void nextPermutation(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        int[] nums = new int[]{1, 2, 3};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 3, 2}, nums);
        nums = new int[]{1, 3, 2};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1, 3}, nums);
        nums = new int[]{3, 2, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
        nums = new int[]{4, 3, 2, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4}, nums);
        nums = new int[]{1, 1, 5};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 5, 1}, nums);
        nums = new int[]{1, 1, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 1, 1}, nums);
        int[][] arrs = new int[][]{{1, 2, 3, 4}, {1, 2, 4, 3}, {1, 3, 2, 4}, {1, 3, 4, 2}, {1, 4, 2, 3}, {1, 4, 3, 2}, {2, 1, 3, 4}, {2, 1, 4, 3}, {2, 3, 1, 4}, {2, 3, 4, 1}, {2, 4, 1, 3}, {2, 4, 3, 1}, {3, 1, 2, 4}, {3, 1, 4, 2}, {3, 2, 1, 4}, {3, 2, 4, 1}, {3, 4, 1, 2}, {3, 4, 2, 1}, {4, 1, 2, 3}, {4, 1, 3, 2}, {4, 2, 1, 3}, {4, 2, 3, 1}, {4, 3, 1, 2}, {4, 3, 2, 1}};
        for (int i = 0; i < arrs.length-1; i++) {
            TLog.e(arrs[i]);
            solution.nextPermutation(arrs[i]);
            TLog.e(arrs[i],2);
            assertArrayEquals(arrs[i+1],arrs[i]);
        }
    }
    private static class Solution2 implements Solution {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void reverse(int[] nums, int start) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }

    private static class Solution1 implements Solution {

        @Override
        public void nextPermutation(int[] nums) {
            int len = nums.length;
            int i = len -1;
            while (i>0&&nums[i-1]>=nums[i])i--;
            if (i>0){
                int j = len -1;
                while (j>=0&& nums[i-1]>=nums[j]) j--;
                swap(nums,i-1,j);
            }

            int j = len -1;
            while (i < j) swap(nums, i++, j--);
        }
        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;

        }
    }
}