package leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * 1030. 距离顺序排列矩阵单元格
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 * <p>
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 * <p>
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 */
public class L1030 {
    private interface Solution {
        int[][] allCellsDistOrder(int R, int C, int r0, int c0);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertArrayEquals(new int[][]{{0, 0}, {0, 1}}, solution.allCellsDistOrder(1, 2, 0, 0));
        assertArrayEquals(new int[][]{{0, 1}, {0, 0}, {1, 1}, {1, 0}}, solution.allCellsDistOrder(2, 2, 0, 1));
        assertArrayEquals(new int[][]{{1, 2}, {0, 2}, {1, 1}, {0, 1}, {1, 0}, {0, 0}}, solution.allCellsDistOrder(2, 3, 1, 2));
    }

    private static class Solution1 implements Solution {

        @Override
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            boolean[][] flag = new boolean[R][C];
            int[][] ans = new int[R * C][];
            int k = 0;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{r0, c0});
            flag[r0][c0] = true;
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                ans[k++] = poll;
                r0 = poll[0];
                c0 = poll[1];

                if (r0 > 0 && !flag[r0 - 1][c0]) {
                    queue.offer(new int[]{r0 - 1, c0});
                    flag[r0 - 1][c0] = true;
                }
                if (c0 > 0 && !flag[r0][c0 - 1]) {
                    queue.offer(new int[]{r0, c0 - 1});
                    flag[r0][c0 - 1] = true;
                }
                if (r0 + 1 < R && !flag[r0 + 1][c0]) {
                    queue.offer(new int[]{r0 + 1, c0});
                    flag[r0 + 1][c0] = true;
                }
                if (c0 + 1 < C && !flag[r0][c0 + 1]) {
                    queue.offer(new int[]{r0, c0 + 1});
                    flag[r0][c0 + 1] = true;
                }
            }
            return ans;
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            boolean[][] flag = new boolean[R][C];
            int[][] ans = new int[R * C][];
            int k = 0;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{r0, c0});
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                r0 = poll[0];
                c0 = poll[1];
                if (flag[r0][c0]) continue;
                flag[r0][c0] = true;
                ans[k++] = poll;
                if (r0 > 0) queue.offer(new int[]{r0 - 1, c0});
                if (c0 > 0) queue.offer(new int[]{r0, c0 - 1});
                if (r0 + 1 < R) queue.offer(new int[]{r0 + 1, c0});
                if (c0 + 1 < C) queue.offer(new int[]{r0, c0 + 1});

            }
            return ans;
        }
    }
    private static class Solution3 implements Solution {

        @Override
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            int[][] ans = new int[R * C][2];
            int dist = 0;
            int cnt = 0;
            int[] factor = {-1, 1};
            while (cnt < R * C) {
                for (int rowDist = 0; rowDist <= dist; rowDist++) {
                    int colDist = dist - rowDist;
                    for (int i = 0; i < 2; i++) {
                        int row = r0 + factor[i] * rowDist;
                        for (int j = 0; j < 2; j++) {
                            int col = c0 + factor[j] * colDist;
                            if (row >= 0 && row < R && col >= 0 && col < C) {
                                ans[cnt][0] = row;
                                ans[cnt][1] = col;
                                cnt++;
                            }
                            if (colDist == 0) break;
                        }
                        if (rowDist == 0) break;
                    }
                }
                dist++;
            }

            return ans;
        }
    }
}