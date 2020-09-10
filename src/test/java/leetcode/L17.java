package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class L17 {
    private interface Solution {
        public List<String> letterCombinations(String digits);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), solution.letterCombinations("234"));
    }

    private static class Solution1 implements Solution {
        private char[][] charsets = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

        @Override
        public List<String> letterCombinations(String digits) {
            int len = digits.length();
            if (len==0)return Collections.EMPTY_LIST;
            int len2 = 1;
            for (int i = 0; i < len; i++) {
                len2 *= charsets[digits.charAt(i) - '2'].length;
            }
            char[][] result = new char[len2][len];
            int tLen = len2;
            for (int i = 0; i < len; i++) {
                char[] cs = charsets[digits.charAt(i) - '2'];
                 tLen /=  cs.length;
                for (int j = 0, k = 0; k<len2; j++) {
                    if (j== cs.length)j=0;
                    for (int l = 0; l < tLen; l++, k++) {
                        result[k][i] = cs[j];
                    }
                }
            }


            List<String> res = new ArrayList<>(len2);
            for (char[] chars : result) {
                res.add(new String(chars));
            }
            return res;

        }
    }
}