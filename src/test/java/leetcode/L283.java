package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class L283 {
    private interface Solution {
        void moveZeroes(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        int[] nums = new int[]{0,1,0,3,12};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1,3,12,0,0}, nums);

        nums = new int[]{1,2,3};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1,2,3}, nums);
    }

    private static class Solution1 implements Solution {

        @Override
        public void moveZeroes(int[] nums) {
            int i=0,j=0,l=nums.length;
            while (i<l) if (nums[i]==0) i++;else nums[j++] = nums[i++];
            while (j<l) nums[j++]=0;
        }
    }
}