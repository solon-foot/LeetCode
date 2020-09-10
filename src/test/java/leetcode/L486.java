package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 486. 预测赢家
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * <p>
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1, 5, 2]
 * 输出：False
 * 解释：一开始，玩家1可以从1和2中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 False 。
 * 示例 2：
 * <p>
 * 输入：[1, 5, 233, 7]
 * 输出：True
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 * 最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于 10000000 。
 * 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
 */
public class L486 {
    private interface Solution {
        boolean PredictTheWinner(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution3();
        assertEquals(false, solution.PredictTheWinner(new int[]{1, 5, 2}));
        assertEquals(false, solution.PredictTheWinner(new int[]{1, 5, 233, 7,6}));
        assertEquals(true, solution.PredictTheWinner(new int[]{1, 5, 233, 7}));
        assertEquals(true, solution.PredictTheWinner(new int[]{1, 1,1}));
        assertEquals(true, solution.PredictTheWinner(new int[]{1, 1}));
        assertEquals(true, solution.PredictTheWinner(new int[]{1}));
    }

    private static class Solution1 implements Solution {

        @Override
        public boolean PredictTheWinner(int[] nums) {
            int len = nums.length;
            dp= new int[len][len];
            for (int i = 0; i < len; i++) {
                Arrays.fill(dp[i],-1);
            }
            int total = 0;
            for (int num : nums) {
                total+=num;
            }
            int max = getMax(0,len-1,nums,total);
//            return (max<<1)>=total;
            return max>=total-max;
        }
        int[][] dp;

        int getMax(int from, int to,int[] nums,int total) {
            if (from==to) return total;
            if (dp[from][to]>=0)return dp[from][to];
            return dp[from][to] = total- Math.min(getMax(from+1,to,nums,total-nums[from]),
                    getMax(from,to-1,nums,total-nums[to]));
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public boolean PredictTheWinner(int[] nums) {
            int length = nums.length;
            int[][] dp = new int[length][length];
            for (int i = 0; i < length; i++) {
                dp[i][i] = nums[i];
            }
            for (int i = length - 2; i >= 0; i--) {
                for (int j = i + 1; j < length; j++) {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
            return dp[0][length - 1] >= 0;
        }
    }
    private static class Solution3 implements Solution {

        @Override
        public boolean PredictTheWinner(int[] nums) {
            int len = nums.length;
            int[] dp = new int[len];
            System.arraycopy(nums,0,dp,0,len);

            for (int i = len - 2; i >= 0; i--) {
                for (int j = i + 1; j < len; j++) {
                    dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
                }
            }
            return dp[len - 1] >= 0;
        }
    }
}