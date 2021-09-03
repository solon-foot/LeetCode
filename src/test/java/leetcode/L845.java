package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 845. 数组中的最长山脉
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * <p>
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * <p>
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * <p>
 * 如果不含有 “山脉” 则返回 0。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 * <p>
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 */
public class L845 {
    private interface Solution {
        int longestMountain(int[] A);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(5, solution.longestMountain(new int[]{2,1,4,7,3,2,5}));
        assertEquals(0, solution.longestMountain(new int[]{2,2,2}));
        assertEquals(7, solution.longestMountain(new int[]{1, 2, 3, 3, 2, 1, 2, 3, 4, 5, 4, 3, 3}));
        assertEquals(0, solution.longestMountain(new int[]{1,2,3}));
        assertEquals(4, solution.longestMountain(new int[]{1,2,3,2}));
        assertEquals(0, solution.longestMountain(new int[]{2,1,2,3}));
        assertEquals(0, solution.longestMountain(new int[]{3,2,1}));
        assertEquals(0, solution.longestMountain(new int[]{3,2,1,2}));
        assertEquals(4, solution.longestMountain(new int[]{1,3,2,1}));
        assertEquals(4, solution.longestMountain(new int[]{875,884,239,731,723,685}));
        assertEquals(4, solution.longestMountain(new int[]{3,4,0,3,2,1}));
        assertEquals(3, solution.longestMountain(new int[]{0,1,0,0,1,0,0,1,1,0,0,0,1,1,0,1,0,1,0,1,0,0,0,1,0,0,1,1,0,1,1,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1,1,1,0,0,0,1,0,0,1,1,0,0,0,1,0,0,1,1,0,0,0,0,1,0,0,1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,0,0,1,0,1,1}));
    }

    private static class Solution1 implements Solution {

        @Override
        public int longestMountain(int[] A) {
            if (A.length <= 2) return 0;
            boolean up = true;
            int ans = 0;
            int start = 0;
            for (int i = 1; i < A.length; i++) {
                if (up) {
                    if (A[i] < A[i - 1]) {
                        if (start == i - 1) {
                            up = true;
                            start = i;
                        } else {
                            up = false;
                        }
                    } else if (A[i] == A[i - 1]) {
                        start = i;
                    }
                } else {
                    if (A[i] >= A[i - 1]) {
                        up = true;
                        ans = Math.max(ans, i - start);
                        i--;
                        start = i;
                    }
                }
            }
            if (!up){
                ans = Math.max(ans,A.length-start);
            }
            return ans;
        }
    }
}