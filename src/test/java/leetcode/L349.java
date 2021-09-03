package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 *
 * 说明：
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class L349 {
    private interface Solution {
        int[] intersection(int[] nums1, int[] nums2);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertArrayEquals(new int[]{2}, solution.intersection(new int[]{1,2,2,1},new int[]{2,2}));
        assertArrayEquals(new int[]{9,4}, solution.intersection(new int[]{4,9,5},new int[]{9,4,9,8,4}));
    }

    private static class Solution1 implements Solution {

        @Override
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            for (int i : nums1) {
                set.add(i);
            }
            List<Integer> list = new ArrayList<>();
            for (int i : nums2) {
                if (set.remove(i)){
                    list.add(i);
                }
            }
            int[] ans = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }


    }
}