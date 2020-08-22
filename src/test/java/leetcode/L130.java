package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class L130 {
    @Test
    public void test() {
        Solution solution = new Solution();
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        char[][] dist = {
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'}};
        solution.solve(board);
        assertArrayEquals(board, dist);
        board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        dist = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'}};
        solution.solve(board);
        assertArrayEquals(board, dist);
        board = new char[][]{
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        dist = new char[][]{
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X'}};
        solution.solve(board);
        assertArrayEquals(board, dist);
//        assertEquals(true, true);
    }

    private static class Solution {
        int w, h;

        public void solve(char[][] board) {
            h = board.length;
            if (h == 0) return;
            w = board[0].length;
            for (int i = 0; i < w; i++) {
                dfs(board, 0, i);
                dfs(board, h - 1, i);
            }
            for (int i = 1; i < h - 1; i++) {
                dfs(board, i, 0);
                dfs(board, i, w - 1);
            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    else if (board[i][j] == 'A') board[i][j] = 'O';
                }
            }
        }

        public void dfs(char[][] board, int i, int j) {
            if (board[i][j] != 'O') return;
            board[i][j] = 'A';
            if (i > 0) dfs(board, i - 1, j);
            if (j > 0) dfs(board, i, j - 1);
            if (i < h - 1) dfs(board, i + 1, j);
            if (j < w - 1) dfs(board, i, j + 1);
        }
    }
}