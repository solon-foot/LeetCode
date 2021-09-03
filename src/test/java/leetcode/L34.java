package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class L34 {
    private interface Solution {
        int[] searchRange(int[] nums, int target);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertArrayEquals(new int[]{3,4}, solution.searchRange(new int[]{5,7,7,8,8,10},8));
        assertArrayEquals(new int[]{5,6}, solution.searchRange(new int[]{5,7,7,8,8,10,10},10));
        assertArrayEquals(new int[]{-1,-1}, solution.searchRange(new int[]{5,7,7,8,8,10},6));
        assertArrayEquals(new int[]{-1,-1}, solution.searchRange(new int[]{},0));
    }

    private static class Solution1 implements Solution {

        @Override
        public int[] searchRange(int[] nums, int target) {
            int i = Arrays.binarySearch(nums, target);
            if (i<0)return new int[]{-1,-1};
            int left = i-1;
            while (left>=0&&nums[left]==target)left--;
            int right = i+1;
            while (right<nums.length&&nums[right]==target)right++;
            return new int[]{left+1,right-1};
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public int[] searchRange(int[] nums, int target) {
            int[] ans = new int[]{-1,-1};
            boolean flag = false;
            for (int i = 0; i < nums.length; i++) {
                if (!flag){
                    if (nums[i]==target){
                     ans[0] = i;
                     ans[1] = nums.length-1;
                        flag = true;
                    }
                } else {
                    if (nums[i]!=target){
                        ans[1] = i-1;
                        break;
                    }
                }
            }
            return ans;
        }
    }
}