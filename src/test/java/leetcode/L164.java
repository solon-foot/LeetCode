package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 164. 最大间距
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 *
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class L164 {
    private interface Solution {
        int maximumGap(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(3, solution.maximumGap(new int[]{3,6,9,1}));
        assertEquals(0, solution.maximumGap(new int[]{10}));
        assertEquals(0, solution.maximumGap(new int[]{}));
    }

    private static class Solution1 implements Solution {

        @Override
        public int maximumGap(int[] nums) {
            Arrays.sort(nums);
            int max = 0;
            for (int i = 1; i < nums.length; i++) max = Math.max(max,nums[i]-nums[i-1]);
            return max;
        }
    }
}