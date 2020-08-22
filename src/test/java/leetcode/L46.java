package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class L46 {
    @Test
    public void test() {
        Solution solution = new Solution();
        TLog.e(solution.permute(new int[]{1, 2, 4}));
        TLog.e(solution.permute(new int[]{1,4}));
        TLog.e(solution.permute(new int[]{1}));
        TLog.e(solution.permute(new int[]{}));
        assertEquals(true, true);
    }

    private static class Solution {
        int[] size = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
        int[] nums;
        List<List<Integer>> data;
        int len;
        public List<List<Integer>> permute(int[] nums) {
            len = nums.length;
            this.nums = nums;
            data = new ArrayList<>(size[len]);
            p(new int[len],0);
            return data;
        }
        void p(int[] a, int cur) {
            if (cur == len) {
                List<Integer> list = new ArrayList<>(len);
                for (int i = 0; i < len; i++) {
                    list.add(nums[a[i]]);
                }
                data.add(list);
                return;
            }
            abc:
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < cur; j++) {
                    if (a[j] == i) continue abc;
                }
                a[cur] = i;
                p(a, cur + 1);
            }
        }
    }
}