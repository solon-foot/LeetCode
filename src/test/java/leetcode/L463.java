package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 463. 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 * <p>
 * <p>
 * 示例 :
 * <p>
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 输出: 16
 * <p>
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 */
public class L463 {
    private interface Solution {
        int islandPerimeter(int[][] grid);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(16, solution.islandPerimeter(new int[][]{{0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}}));
    }

    private static class Solution1 implements Solution {

        @Override
        public int islandPerimeter(int[][] grid) {
            ans = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        dfs(grid, i, j);
                        break;
                    }
                }
            }
            return ans;
        }

        int ans = 0;

        private void dfs(int[][] grid, int i, int j) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0) {
                ans++;
                return;
            }
            if (grid[i][j] == 2) return;
            grid[i][j] = 2;
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public int islandPerimeter(int[][] grid) {
            int a = grid.length;
            int b = grid[0].length;
            int res = 0;
            for (int i = 0; i < a; i++)
                for (int j = 0; j < b; j++)
                    if (grid[i][j] == 1) {
                        res += 4;
                        if (i > 0 && grid[i - 1][j] == 1) res -= 2;
                        if (j > 0 && grid[i][j - 1] == 1) res -= 2;
                    }
            return res;
        }
    }
}