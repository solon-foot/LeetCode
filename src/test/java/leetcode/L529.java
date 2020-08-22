package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 529. 扫雷游戏
 * 让我们一起来玩扫雷游戏！
 * <p>
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * <p>
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 * <p>
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * <p>
 * [['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'M', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E']]
 * <p>
 * Click : [3,0]
 * <p>
 * 输出:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * 解释:
 * <p>
 * 示例 2：
 * <p>
 * 输入:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * Click : [1,2]
 * <p>
 * 输出:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'X', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * 解释:
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 输入矩阵的宽和高的范围为 [1,50]。
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
 * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
 * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况
 */
public class L529 {
    @Test
    public void test() {
        Solution solution = new Solution();
        char[][] board = {{'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        char[][] out = {{'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};
        int[] click = {3, 0};
        assertArrayEquals(out, solution.updateBoard(board, click));
        board = new char[][]{{'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};

        out = new char[][]{{'B', '1', 'E', '1', 'B'},
                {'B', '1', 'X', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};
        click = new int[]{1, 2};
        assertArrayEquals(out, solution.updateBoard(board, click));
        board = new char[][] {{'E','E','E','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'}};
        click = new int[]{0,0};
        out = new char[][] {{'B','B','B','B','B'},
                {'B','B','B','B','B'},
                {'B','B','B','B','B'},
                {'B','B','B','B','B'},
                {'B','B','B','B','B'},
                {'B','B','B','B','B'}};

        assertArrayEquals(out, solution.updateBoard(board, click));

    }

    private static class Solution {
        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0];
            int y = click[1];
            if (board[x][y]=='M'){
                board[x][y]='X';
                return board;
            }
            return updateBoard(board, x,y);
        }

        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        boolean isInBoard(char[][] board, int x, int y) {
            return x >= 0 && y >= 0 && x < board.length && y < board[0].length;
        }

        public char[][] updateBoard(char[][] board, final int x, final int y) {
            char count = '0';
            for (int[] dir : dirs) {
                int tx = x + dir[0];
                int ty = y + dir[1];
                if (isInBoard(board, tx, ty) && board[tx][ty] == 'M') count++;
            }
            if (count == '0') {
                board[x][y] = 'B';
                for (int[] dir : dirs) {
                    int tx = x + dir[0];
                    int ty = y + dir[1];
                    if (isInBoard(board, tx, ty)&&board[tx][ty]!='B') updateBoard(board, tx, ty);
                }
            } else
                board[x][y] = count;
            return board;
        }
    }

}