package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class L216 {
    private interface Solution {
        List<List<Integer>> combinationSum3(int k, int n);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
//        assertEquals(true, solution.combinationSum3(3,7));
        assertEquals(true, solution.combinationSum3(3,9));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            dfs(k, n, 9, new ArrayList<>(k), ans);
            return ans;
        }
        void dfs(int k,int n, int i, ArrayList<Integer> temp, List<List<Integer>> ans) {
            if (n<0)return;
            if (k==0){

                if (n==0)
                ans.add(new ArrayList<>(temp));
                return;
            }
            for (int j = i; j >= k; j--) {
                if (j>n) continue;
                temp.add(j);
                dfs(k-1,n-j,j-1,temp,ans);
                temp.remove(temp.size()-1);
            }
        }
    }
}