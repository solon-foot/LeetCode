package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L37 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
        char[][] chars = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        String[] strs = {"000010054800000000000000000650400000000002730000000000210000800700000300000350000",
                "570120000000000000006700080304009070020070050010300902080002100000000000000054063",
                "140750000000000000005100030901005080070010050080300406060002700000000000000084029",
                "910250000000000000002100060704009030060080010050300608030006500000000000000097041",
                "570890000000000000004600030302001060010080090080700403040009600000000000000035042",
                "430280000000000000008400010903002040070030020050900801020006500000000000000041076",
                "640230000000000000002100070104003050070010090090600208050004600000000000000068047"};
        String[] ans = {
                "379816254864275193521943687657431928498562731132789546213694875745128369986357412",
                "578126439932548617146793285354269871829471356617385942785632194463917528291854763",
                "148753692623849175795126834931465287476218953582397416869532741254971368317684529",
                "916253784378964152542178963784619235263785419159342678831426597497531826625897341",
                "576893124931524786824617539352941867417386295689752413143279658295468371768135942",
                "431285769792613458568479312913852647876134925254967831127396584645728193389541276",
                "647239185981475362532186974124893756876512493395647218758924631463751829219368547"
        };
    }
    public static String boardToStr(char[][] board) {
        StringBuilder sb = new StringBuilder(81);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') c = '0';
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static char[][] toBoard(String str) {
        char[][] board = new char[9][9];
        for (int i = 0, k = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++, k++) {
                board[i][j] = str.charAt(k);
                if (board[i][j] == '0') {
                    board[i][j] = '.';
                }
            }
        }
        return board;
    }


    private static void print(char[][] board) {
        StringBuilder sb = new StringBuilder();

        sb.append("╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗\n");
        char temp = 0;
        for (int i = 0; i < 9; i++) {
            sb.append("║");
            for (int j = 0; j < 9; j++) {
                temp = board[i][j];
                if (temp == '.') {
                    sb.append("   ");
                } else {
                    sb.append(' ').append(temp).append(' ');
                    // sb+=(String.format("%02d ", temp));

                }
                if (j == 2 || j == 5 || j == 8) {
                    sb.append('║');
                } else {
                    sb.append('│');
                }

            }
            sb.append('\n');
            if (i == 8) {
                sb.append("╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝\n");
            } else {
                if (i == 2 || i == 5) {
                    sb.append("╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣\n");
                } else {
                    sb.append("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢\n");
                }
            }
        }
        System.out.println(sb);
    }
    private static class Solution {
        public void solveSudoku(char[][] board) {
            solveSudoku(board, 0, -1, '0');
        }

        public boolean solveSudoku(char[][] board, int x, int y, char cc) {
            if (y != -1) board[x][y] = cc;
            int i = x;
            int j = y + 1;//从下一个位置开始查找 ‘.’

            for (; i < 9; i++, j = 0) {
                for (; j < 9; j++) {
                    if (board[i][j] == '.') {
                        int tx = i / 3 * 3;
                        int ty = j / 3 * 3;
                        for (char c : cs) {
                            //判断是否可以填入指定数字，如果可以，尝试解决指定结果后的棋盘
                            if (judge(board, i, j, c, tx, ty) && solveSudoku(board, i, j, c)) return true;
                        }
                        board[x][y] = '.';
                        return false;
                    }
                }
            }
            return true;
        }

        int[] xx = {0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] yy = {0, 1, 2, 0, 1, 2, 0, 1, 2};
        char[] cs = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        private boolean judge(char[][] board, int x, int y, char c, int tx, int ty) {
//        int tx = x / 3 * 3; //写这里估计也耗不了多少性能
//        int ty = y / 3 * 3;
            for (int i = 0; i < 9; i++) {
                if (i != y && board[x][i] == c) return false;
                if (i != x && board[i][y] == c) return false;
                if (board[tx + xx[i]][ty + yy[i]] == c) return false;
            }
            return true;
        }


    }

    private static class Solution2 {

        int[] rows;
        int[] cols;
        int[][] cells;

        int getPossibleStatus(int x, int y) {
            return ~(rows[x] | cols[y] | cells[x / 3][y / 3]);
        }

        int sizeOf(int bs) {
            int count = 0;
            for (int i = 0; i < 9; i++) {
                if ((bs & (1 << i)) != 0) count++;
            }
            return count;
        }

        int[] getNext(char[][] board) {
            int[] ret = {0, 0};
            int minCnt = 10;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') continue;
                    int cur = getPossibleStatus(i, j);
                    cur = sizeOf(cur);
                    if (cur >= minCnt) continue;
                    ret[0] = i;
                    ret[1] = j;
                    minCnt = cur;
                    if (cur == 0) return ret;
                }
            }
            return ret;
        }

        void fillNum(int x, int y, int n, boolean fillFlag) {
            int pick = 1 << n;
            rows[x] = (fillFlag) ? (rows[x] | pick) : (rows[x] ^ pick);
            cols[y] = (fillFlag) ? (cols[y] | pick) : (cols[y] ^ pick);
            cells[x / 3][y / 3] = (fillFlag) ? (cells[x / 3][y / 3] | pick) : (cells[x / 3][y / 3] ^ pick);
        }

        boolean dfs(char[][] board, int cnt) {
            if (cnt == 0) return true;

            int[] next = getNext(board);
            int bits = getPossibleStatus(next[0], next[1]);
            for (int n = 0; n < 9; n++) {
                if ((bits & (1 << n)) == 0) continue;
                fillNum(next[0], next[1], n, true);
                board[next[0]][next[1]] = (char) (n + '1');
                if (dfs(board, cnt - 1)) return true;
                board[next[0]][next[1]] = '.';
                fillNum(next[0], next[1], n, false);
            }
            return false;
        }

        void solveSudoku(char[][] board) {
            rows = new int[9];
            cols = new int[9];
            cells = new int[3][3];

            int cnt = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        cnt++;
                        continue;
                    }
                    int n = board[i][j] - '1';
                    rows[i] |= (1 << n);
                    cols[j] |= (1 << n);
                    cells[i / 3][j / 3] |= (1 << n);
                }
            }
            printBig(board);
            dfs(board, cnt);
        }
        //        char[] numChar = {'①','②','③','④','⑤','⑥','⑦','⑧','⑨'};
//        char[] numChar = {'❶','❷','❸','❹','❺','❻','❼','❽','❾'};
//        char[] numChar = {'⒈','⒉','⒊','⒋','⒌','⒍','⒎','⒏','⒐'};
        char[] numChar = {'a','b','c','f','e','f','g','h','i'};
        public  void printBig(char[][]board) {
            StringBuilder sb = new StringBuilder();
            String s1, s2, s3;
            char b;
            sb .append( "╔═══════╤═══════╤═══════╦═══════╤═══════╤═══════╦═══════╤═══════╤═══════╗\n");
            for (int i = 0; i < 9; i++) {
                s1 = "║";
                s2 = "║";
                s3 = "║";
                for (int j = 0; j < 9; j++) {
                    if (board[i][j]!='.'){
                        s1 += "       ";
                        s2 += "   "+numChar[board[i][j]-'1']+"   ";
                        s3 += "       ";
                    } else {
                        int bits = getPossibleStatus(i, j);

                        for (int l = 0; l < 3; l++) {
                            if((bits & (1 << l))!=0){
                                s1+=" " + (l+1);
                            } else {
                                s1 +="  ";
                            }
                            if((bits & (1 << (l+3)))!=0){
                                s2+=" " + (l+4);
                            } else {
                                s2 +="  ";
                            }
                            if((bits & (1 << (l+6)))!=0){
                                s3+=" " + (l+7);
                            } else {
                                s3 +="  ";
                            }

                        }
                        s1+=' ';
                        s2+=' ';
                        s3+=' ';
                    }
                    if (j == 2 || j == 5 || j == 8) {
                        s1 += "║";
                        s2 += "║";
                        s3 += "║";
                    } else {
                        s1 += "│";
                        s2 += "│";
                        s3 += "│";
                    }
                }
                sb.append(s1 + "\n" + s2 + "\n" + s3 + "\n");

                if (i == 8) {
                    sb .append("╚═══════╧═══════╧═══════╩═══════╧═══════╧═══════╩═══════╧═══════╧═══════╝\n");
                } else {
                    if (i == 2 || i == 5) {
                        // sb+=("┣━━╋━━╋━━╋━━╋━━┫\n");
                        sb .append("╠═══════╪═══════╪═══════╬═══════╪═══════╪═══════╬═══════╪═══════╪═══════╣\n");
                    } else {
                        sb .append( "╟───────┼───────┼───────╫───────┼───────┼───────╫───────┼───────┼───────╢\n");
                    }
                }
            }

            System.out.println(sb);
        }
    }

}