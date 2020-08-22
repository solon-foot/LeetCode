package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 *
 * 输入的字符串长度不会超过 1000 。
 */
public class L647 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(3, solution.countSubstrings("abc"));
        assertEquals(6, solution.countSubstrings("aaa"));
        assertEquals(6, solution.countSubstrings("fdsklf"));
    }

    private static class Solution {
        public int countSubstrings(String s) {
            int ans = 0;
            int len = s.length();
            for (int i = 0; i < len; i++) {
                int j=1;
                ans++;
                while (i-j>=0&&i+j<len&&s.charAt(i-j)==s.charAt(i+j)){ans++;j++;}
                j=0;
                while (i-j>=0&&i+j+1<len&&s.charAt(i-j)==s.charAt(i+j+1)){ans++;j++;}
            }
            return ans;
        }
    }
}