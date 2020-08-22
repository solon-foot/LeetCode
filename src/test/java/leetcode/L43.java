package leetcode;

import org.junit.Test;

import java.io.DataOutput;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L43 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(solution.multiply("2", "3"), "6");
        assertEquals(solution.multiply("3", "456"), "1368");
        assertEquals(solution.multiply("123", "456"), "56088");
        assertEquals(solution.multiply("123", "0"), "0");
        assertEquals(solution.multiply("0", "123"), "0");
    }

    private static class Solution {
        public String multiply(String num1, String num2) {
            if ("0".equals(num1) || "0".equals(num2)) return "0";
            int len = num1.length() + num2.length();
            char[] ans = new char[len];
            int x, y;
            for (int i = num1.length() - 1; i >= 0; i--) {
                x = num1.charAt(i) - '0';
                for (int j = num2.length() - 1; j >= 0; j--) {
                    y = num2.charAt(j) - '0';
                    ans[i + j + 1] += x * y;
                }
            }

            for (int i = len - 1; i > 0; i--) {
                ans[i - 1] += ans[i] / 10;
                ans[i] %= 10;
                ans[i] += '0';
            }
            if (ans[0] == 0) return new String(ans, 1, len - 1);
            ans[0] = (char) (ans[0] + '0');
            return new String(ans);
        }

    }
}