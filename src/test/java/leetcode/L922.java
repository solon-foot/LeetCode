package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 922. 按奇偶排序数组 II
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * <p>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class L922 {
    private interface Solution {
        int[] sortArrayByParityII(int[] A);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        TLog.e(solution.sortArrayByParityII(new int[]{4, 2, 5, 7}));
        TLog.e(solution.sortArrayByParityII(new int[]{1, 2, 3, 4, 5, 6}));
        TLog.e(solution.sortArrayByParityII(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4}));


    }

    private static class Solution1 implements Solution {

        @Override
        public int[] sortArrayByParityII(int[] A) {

            for (int i = 0, j = 1; i < A.length; i += 2)
                if ((A[i] & 1) == 1) {
                    while ((A[j] & 1) == 1) j += 2;
                    int t = A[i];
                    A[i] = A[j];
                    A[j] = t;
                    j += 2;
                }

            return A;
        }
    }
}