package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * 977. 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 */
public class L977 {
    private interface Solution {
        int[] sortedSquares(int[] A);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertArrayEquals(new int[]{0,1,9,16,100}, solution.sortedSquares(new int[]{-4,-1,0,3,10}));
        assertArrayEquals(new int[]{4,9,9,49,121}, solution.sortedSquares(new int[]{-7,-3,2,3,11}));
    }

    private static class Solution1 implements Solution {

        @Override
        public int[] sortedSquares(int[] A) {
            int len = A.length;
            int[] ans = new int[len];
            int start = 0;
            for (int i = 0; i < len; i++) {
                if (A[i]>=0){
                    start = i;
                    break;
                }
            }
            int i=0,j= start;
            int index = 0;
            while (index<len){
                if (i==start){
                    ans[index++] = A[j]*A[j];
                    j++;
                } else if (j==len){
                    ans[index++] = A[i]*A[i];
                    i++;
                } else {
                    if (-A[i]>A[j]) {
                        ans[index++] = A[j]*A[j];
                        j++;
                    } else {
                        ans[index++] = A[i]*A[i];
                        i++;
                    }
                }
            }
            TLog.e(ans);
            return ans;
        }
    }
}