package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 696. 计数二进制子串
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 *
 */
public class L696 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(6, solution.countBinarySubstrings("00110011"));
        assertEquals(4, solution.countBinarySubstrings("000011111"));
        assertEquals(8, solution.countBinarySubstrings("111111000011111"));
        assertEquals(4, solution.countBinarySubstrings("10101"));
        assertEquals(0, solution.countBinarySubstrings("1"));
        assertEquals(1, solution.countBinarySubstrings("10"));
    }

    private static class Solution {
        public int countBinarySubstrings(String s) {
            char c,lastChar=0;
            int ans = 0;
            int last = 0;
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c ==lastChar){
                    count++;
                    continue;
                }
                lastChar = c;
                ans += Math.min(count, last);
                last = count;
                count = 1;
            }
            ans += Math.min(count, last);
            return ans;
        }
//        public int countBinarySubstrings(String s) {
//            int i = 0, n = s.length(), last = 0, ans = 0;
//            while (i < n) {
//                char c = s.charAt(i++);
//                int count = 1;
//                while (i < n && s.charAt(i) == c) {
//                    ++i;
//                    ++count;
//                }
//                ans += Math.min(count, last);
//                last = count;
//            }
//            return ans;
//        }
    }
}