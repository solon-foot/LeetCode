package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class L51 {
    private interface Solution {
        List<List<String>> solveNQueens(int n);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, true);
        for (int i = 0; i < 15; i++) {
            List<List<String>> lists = solution.solveNQueens(i);
            if (!lists.isEmpty())
            TLog.e(i,lists.size());
        }

    }

    private static class Solution1 implements Solution {

        @Override
        public List<List<String>> solveNQueens(int n) {
             cs = new char[n][n];
            flag = new int[n][n];
            len = n;
            for (int i = 0; i < n; i++) {
                Arrays.fill(cs[i], '.');
                Arrays.fill(flag[i], -1);
            }

                ans = new ArrayList<>();
            _solveNQueens(0);

            return ans;
        }
        char[][] cs ;
        int[][] flag;
        int len;
        List<List<String>> ans;
        void _solveNQueens(int start) {
            if (start == len) {
                List<String> res = new ArrayList<>(len);
                for (char[] c : cs) {
                    res.add(String.valueOf(c));
                }
                ans.add(res);
                return;
            }

            for (int i = 0; i < len; i++) {
                if (flag[start][i] != -1) continue;
                set(start, i, -1, start);
                cs[start][i] = 'Q';
                _solveNQueens( start + 1);
                cs[start][i] = '.';
                set(start, i, start, -1);
            }
        }

        void set(final int i, final int j, final int a, final int b) {
            for (int k = 0; k < len; k++) {
                if (flag[i][k] == a) flag[i][k] = b;
                if (flag[k][j] == a) flag[k][j] = b;
            }

            int ii = i;
            int jj = j;
            while (--ii >= 0 && --jj >= 0) if (flag[ii][jj] == a) flag[ii][jj] = b;
            ii = i;
            jj = j;
            while (++ii < len && ++jj < len) if (flag[ii][jj] == a) flag[ii][jj] = b;
            ii = i;
            jj = j;
            while (--ii >= 0 && ++jj < len) if (flag[ii][jj] == a) flag[ii][jj] = b;
            ii = i;
            jj = j;
            while (++ii < len && --jj >= 0) if (flag[ii][jj] == a) flag[ii][jj] = b;

        }

    }

}