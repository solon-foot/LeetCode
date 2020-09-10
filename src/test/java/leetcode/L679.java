package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 * <p>
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 注意:
 * <p>
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/24-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L679 {
    private interface Solution {
        boolean judgePoint24(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, solution.judgePoint24(new int[]{4, 1, 8, 7}));
        assertEquals(false, solution.judgePoint24(new int[]{1, 2, 1, 2}));
    }

    private static class Solution1 implements Solution {

        int[][] p = {{0, 1, 2, 3}, {0,2,1,3},{0,2,3,1},{0,3,1,2},{0,3,2,1},
                {1,2,0,3},{1,2,3,0},{1,3,0,2},{1,3,2,0},
                {2,3,0,1},{2,3,1,0}
        };

        @Override
        public boolean judgePoint24(int[] nums) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < 6; k++) {
                        for (int[] ints : p) {
                            if (isEqual24(cal(cal(cal(nums[ints[0]], nums[ints[1]], i), nums[ints[2]], j), nums[ints[3]], k))) {
                                return true;
                            }
                        }

                    }
                    for (int k = 3; k < 6; k++) {
                        if (isEqual24(cal(cal(nums[0], nums[1], i), cal(nums[2], nums[3], j), k))) return true;
                        if (isEqual24(cal(cal(nums[0], nums[2], i), cal(nums[1], nums[3], j), k))) return true;
                        if (isEqual24(cal(cal(nums[0], nums[3], i), cal(nums[1], nums[2], j), k))) return true;
                    }
                }
            }
            return false;
        }

        boolean isEqual24(float a) {
            return (a <= 24.000001f && a >= 23.99999f);
        }

        float cal(float a, float b, int m) {
            switch (m) {
                case 0:
                    return a + b;
                case 1:
                    return a - b;
                case 2:
                    return b - a;
                case 3:
                    return a * b;
                case 4:
                    if (a == 0) return 0;
                    return b / a;
                case 5:
                    if (b == 0) return 0;
                    return a / b;
                    default:
                return 0;
            }
        }
    }
}