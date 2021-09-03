package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class L22 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, solution.generateParenthesis(3));
    }

    private static class Solution {
        List<String> res = new ArrayList<>();
        public List<String> generateParenthesis(int n) {
            if(n <= 0){
                return res;
            }
            getParenthesis(new StringBuilder(),n,n);
            return res;
        }

        private void getParenthesis(StringBuilder str,int left, int right) {
            if(left == 0 && right == 0 ){
                res.add(str.toString());
                return;
            }
            int len = str.length();
            if(left == right){
                //剩余左右括号数相等，下一个只能用左括号
                str.append('(');
                getParenthesis(str,left-1,right);
                str.setLength(len);

            }else if(left < right){
                //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
                if(left > 0){
                    str.append('(');
                    getParenthesis(str,left-1,right);
                    str.setLength(len);
                }
                str.append(')');
                getParenthesis(str,left,right-1);
                str.setLength(len);
            }
        }
    }
}