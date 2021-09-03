package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 454. 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class L454 {
    private interface Solution {
        int fourSumCount(int[] A, int[] B, int[] C, int[] D);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(2, solution.fourSumCount(new int[]{1,2},new int[]{-2,-1},new int[]{-1,2},new int[]{0,2}));
        assertEquals(17, solution.fourSumCount(new int[]{0,1,-1},new int[]{-1,1,0},new int[]{0,0,1},new int[]{-1,1,1}));
    }

    private static class Solution2 implements Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
            for (int u : A) {
                for (int v : B) {
                    countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
                }
            }
            int ans = 0;
            for (int u : C) {
                for (int v : D) {
                    if (countAB.containsKey(-u - v)) {
                        ans += countAB.get(-u - v);
                    }
                }
            }
            return ans;
        }
    }
    private static class Solution1 implements Solution {

        @Override
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            int n = A.length;
            if (n==0)return 0;
            Arrays.sort(A);
            Arrays.sort(B);
            Arrays.sort(C);
            Arrays.sort(D);
            int ans = 0;
            for (int i = 0; i < n; i++) {
                int temp = 0;
                for (int j = 0; j < n; j++) {
                    int temp2 = 0;
                    int t = A[i] + B[j];
                    int l = n-1;
                    for (int k = 0; k < n&&l>=0; k++) {
                        int temp3 = 0;
                        while (l>=0&&t+C[k]+D[l]>0)l--;
                        while (l>=0 && t+C[k]+D[l]==0){
                            temp3++;
                            l--;
                        }
                        int p3 = 1;
                        while (k+1<n && C[k+1]==C[k]){p3++;k++;};
                        temp2 += temp3* p3;
                    }
                    int p2 = 1;
                    while (j+1<n && B[j+1]==B[j]){p2++;j++;}
                    temp += temp2*p2;
                }
                int p = 1;
                while (i+1<n && A[i+1] == A[i]){p++;i++;}
                ans += temp * p;
            }
            return ans;
        }
    }
}