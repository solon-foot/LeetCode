package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * 557. 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class L557 {
    private interface Solution {
        String reverseWords(String s);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals("s'teL ekat edoCteeL tsetnoc", solution.reverseWords("Let's take LeetCode contest"));

        assertEquals("", solution.reverseWords(""));
    }

    private static class Solution1 implements Solution {

        @Override
        public String reverseWords(String s) {
            char[] cs = s.toCharArray();
            char[] ans = new char[cs.length];

            int i = 0;
            int start = 0;
            do {
                while (i < cs.length && cs[i] != ' ') i++;
                for (int j = start; j < i; j++) {
                    ans[j] = cs[start + i - j - 1];
                }
                if (i == cs.length) break;

                ans[i++] = ' ';
                start = i;
            } while (true);
            return new String(ans);
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public String reverseWords(String s) {
            return Stream.of(s.split(" ")).map(t-> new StringBuilder(t).reverse()).collect(Collectors.joining(" "));
        }
    }

}