package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L4 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int[] nums = new int[m + n];
            int i = m - 1, j = n - 1, k = m + n - 1;
            while (i >= 0 && j >= 0) nums[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
            while (j >= 0) nums[k--] = nums2[j--];
            while (i >= 0) nums[k--] = nums1[i--];
            k = nums.length - 1;
            if ((k & 1) == 1) {
                k = k >> 1;
                return (nums[k] + nums[k + 1]) / 2.0;
            }
            k = k >> 1;
            return nums[k];

        }
    }
    private static class Solution2 {
        /**
         * 抄袭并修改评论区的结果
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int left = (m + n + 1) >>1;
            int right = (m + n + 2)>>1;
            if (left == right) return findKth(nums1, nums2, left);
            return (findKth(nums1, nums2, left) + findKth(nums1, nums2, right)) / 2.0;
        }

        public int findKth(int[] nums1, int[] nums2, int k) {
            int m = nums1.length;
            int n = nums2.length;
            int i = 0;
            int j = 0;
            while (true) {
                if (i >= m) return nums2[j + k - 1];//nums1为空数组
                if (j >= n) return nums1[i + k - 1];//nums2为空数组
                if (k == 1) {
                    return Math.min(nums1[i], nums2[j]);
                }
                if (j + k / 2 - 1 >= n || i + k / 2 - 1 < m && nums1[i + k / 2 - 1] < nums2[j + k / 2 - 1]) {
                    i += k / 2;
                    k -= k / 2;
                } else {
                    j += k / 2;
                    k -= k / 2;
                }
            }
        }

//        public int findKth(int[] nums1, int[] nums2, int k) {
//            int m = nums1.length;
//            int n = nums2.length;
//            int i = 0;
//            int j = 0;
//            while (true) {
//                if (i >= m) return nums2[j + k ];
//                if (j >= n) return nums1[i + k ];
//                if (k == 1) {
//                    return Math.min(nums1[i], nums2[j]);
//                }
//                if (j + k / 2 > n || i + k / 2 +1< m && nums1[i + k / 2] < nums2[j + k / 2]) {
//                    i += k / 2;
//                    k -= k / 2;
//                } else {
//                    j += k / 2;
//                    k -= k / 2;
//                }
//            }
//        }

    }
}