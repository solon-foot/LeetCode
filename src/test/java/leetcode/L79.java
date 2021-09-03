package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * <p>
 * 提示：
 * <p>
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class L79 {
    private interface Solution {
        boolean exist(char[][] board, String word);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertEquals(true, solution.exist(board, "ABCCED"));
//        assertEquals(true, solution.exist(board,"SEE"));
//        assertEquals(false, solution.exist(board,"ABCB"));
//        board = new char[][]{{'a', 'a'}};
//        assertEquals(false, solution.exist(board, "aaa"));

    }

    private static class Solution1 implements Solution {

        @Override
        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        if (dfs(board, word, i, j, 1)) return true;
                    }
                }

            }
            return false;

        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        boolean dfs(char[][] board, String word, int i, int j, int k) {
            if (word.length() == k) return true;
            char tc = board[i][j];
            board[i][j] = 0;
            char c = word.charAt(k);
            ++k;
            for (int[] dir : dirs) {
                int x = dir[0] + i;
                int y = dir[1] + j;
                if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == c) {
                    if (dfs(board, word, x, y, k)) {
//                        board[i][j]=tc;
                        return true;
                    }
                }

            }
            board[i][j] = tc;
            return false;
        }
    }
}