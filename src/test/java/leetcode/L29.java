package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 29. 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *
 *
 * 提示：
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class L29 {
    private interface Solution {
        int divide(int dividend, int divisor);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(3, solution.divide(10,3));
        assertEquals(-2, solution.divide(7,-3));
        assertEquals(-3, solution.divide(9,-3));
        assertEquals(3, solution.divide(9,3));
        assertEquals(3, solution.divide(-9,-3));
        assertEquals(9, solution.divide(-9,-1));
        assertEquals(-9, solution.divide(9,-1));
    }

    private static class Solution1 implements Solution {

        @Override
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1)return Integer.MAX_VALUE;
            boolean flag =false;
            if (dividend>0){
                dividend=-dividend;
                flag = !flag;
            }
            if (divisor>0){
                divisor=-divisor;
                flag = !flag;
            }
            int res = 0;
            while (dividend<=divisor){
                dividend -= divisor;
                res++;
            }


            return flag?-res:res;
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public int divide(int dividend, int divisor) { // 被除数 除数
            if(divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE; // 溢出
            boolean sign = false;
            if((dividend > 0 && divisor < 0)||(dividend < 0 && divisor > 0))
                sign = true;
            if(divisor == 1) return dividend;
            if(divisor == -1) return -dividend;
            int a = dividend>0 ? -dividend : dividend;
            int b = divisor>0 ? -divisor : divisor;
            // 都改为负号是因为int 的范围是[2^32, 2^32-1]，如果a是-2^32，转为正数时将会溢出
            //System.out.println(a + " " + b);
            if(a > b) return 0;
            int ans = div(a,b);
            return sign ? -ans : ans;
        }
        int div(int a, int b)
        {
            if(a > b) return 0;
            int count = 1;
            int tb = b;
            while(tb+tb >= a && tb+tb < 0){ // 溢出之后不再小于0
                tb += tb;
                count += count;
                //System.out.println(tb + " " + count + " " + count*b);
            }
            return count+div(a-tb,b);
        }
    }
}