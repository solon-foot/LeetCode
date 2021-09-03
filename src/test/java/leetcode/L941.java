package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 941. 有效的山脉数组
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * <p>
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * <p>
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：[3,5,5]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：[0,3,2,1]
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 */
public class L941 {
    private interface Solution {
        boolean validMountainArray(int[] A);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(true, solution.validMountainArray(new int[]{0, 3, 2, 1}));
        assertEquals(true, solution.validMountainArray(new int[]{0, 3, 2}));
        assertEquals(false, solution.validMountainArray(new int[]{3, 5, 5}));
        assertEquals(false, solution.validMountainArray(new int[]{2, 1}));
        assertEquals(false, solution.validMountainArray(new int[]{1,2,3}));
        assertEquals(false, solution.validMountainArray(new int[]{3,2,1}));
    }

    private static class Solution1 implements Solution {

        @Override
        public boolean validMountainArray(int[] A) {
            if (A.length < 3) return false;
            if (A[1]<=A[0])return false;
            int i = 2;
            for (; i < A.length; i++)
                if (A[i] < A[i - 1])  break;
                else if (A[i] == A[i - 1]) return false;
            for (; i < A.length; i++) if (A[i] >= A[i - 1]) return false;
            return A[i-1]<A[i-2];
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public boolean validMountainArray(int[] A) {
            int N = A.length;
            int i = 0;
            while (i + 1 < N && A[i] < A[i + 1])i++;
            if (i == 0 || i == N - 1) return false;
            while (i + 1 < N && A[i] > A[i + 1]) i++;
            return i == N - 1;
        }
    }
}