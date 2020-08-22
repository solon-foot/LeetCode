package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 1536. 排布二进制网格的最少交换次数
 * 给你一个 n x n 的二进制网格 grid，每一次操作中，你可以选择网格的 相邻两行 进行交换。
 *
 * 一个符合要求的网格需要满足主对角线以上的格子全部都是 0 。
 *
 * 请你返回使网格满足要求的最少操作次数，如果无法使网格符合要求，请你返回 -1 。
 *
 * 主对角线指的是从 (1, 1) 到 (n, n) 的这些格子。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[0,0,1],[1,1,0],[1,0,0]]
 * 输出：3
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * 输出：-1
 * 解释：所有行都是一样的，交换相邻行无法使网格符合要求。
 * 示例 3：
 *
 *
 *
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,1]]
 * 输出：0
 *
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 200
 * grid[i][j] 要么是 0 要么是 1 。
 */
public class L1536 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        TLog.e(solution.minSwaps(new int[][]{{0,0,1},{1,1,0},{1,0,0}}));
        TLog.e(solution.minSwaps(new int[][]{{0,1,1,0},{0,1,1,0},{0,1,1,0},{0,1,1,0}}));
        TLog.e(solution.minSwaps(new int[][]{{1,0,0},{1,1,0},{1,1,1}}));
    }

    private static class Solution {
        public int minSwaps(int[][] grid) {
            int len = grid.length;
//        int[] rows = new int[len];
            List<Integer> rows= new ArrayList<>();
            for (int i = 0; i < len; i++) {
                int count = 0;
                int[]gd = grid[i];
                for (int j = len - 1; j >= 0; j--) {
                    if (gd[j]!=0)break;
                    count++;
                }
                rows.add(count);
            }
            int count = 0;
            abc:for (int i = 0; i < len; i++) {
                if (rows.get(i)>=len-i-1)continue;
                for (int j = i+1; j < len; j++) {
                    if (rows.get(j)>=len-i-1){
                        count += j-i;
                        Integer t= rows.remove(j);
                        rows.add(i,t);
                        continue abc;
                    }
                }
                return -1;
            }
            return count;
        }
    }
}