package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class L77 {
    private interface Solution {
        List<List<Integer>> combine(int n, int k);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, true);
        TLog.e(solution.combine(4, 2));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<List<Integer>> combine(int n, int k) {
            int res = cnk(n, k);
            List<List<Integer>> ans = new ArrayList<>(res);
            dfs(ans, new ArrayList<>(k), 0, n, k - 1);
            return ans;
        }

        void dfs(List<List<Integer>> ans, List<Integer> res, int i, int n, int k) {
            if (k == -1) {
                ans.add(new ArrayList<>(res));
                return;
            }
            for (int j = i; j < n - k; j++) {
                res.add(j + 1);
                dfs(ans, res, j + 1, n, k - 1);
                res.remove(res.size() - 1);
            }

        }

        int cnk(int n, int k) {
            k = Math.min(k, n - k);
            int res = 1;
            for (int i = 0; i < k; i++) {
                res *= n;
                res /= (i + 1);
                n--;
            }
            return res;
        }
    }
}