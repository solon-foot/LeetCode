package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * <p>
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * <p>
 * S 只包含小写字母并且长度在[1, 500]区间内。
 */
public class L767 {
    private interface Solution {
        String reorganizeString(String S);
    }

    @Test
    public void test() {
        // aaaabb bbcccc  ababacacbcbc
        Solution solution = new Solution1();
        assertEquals("aba", solution.reorganizeString("aab"));
        assertEquals("", solution.reorganizeString("aaab"));
        assertEquals("ababa", solution.reorganizeString("aaabb"));
        assertEquals("ababacacbcbc", solution.reorganizeString("aaaabbbbcccc"));
        assertEquals("abacacbcb", solution.reorganizeString("aaabbbccc"));
    }

    private static class Solution1 implements Solution {

        @Override
        public String reorganizeString(String S) {
            int len = S.length();
            int[][] count = new int[26][2];
            for (int i = 0; i < len; i++) {
                count[S.charAt(i) - 'a'][0]++;
            }
            for (int i = 0; i < count.length; i++) {
                count[i][1] = i + 'a';
                char c = (char) count[i][1];
            }
            Arrays.sort(count, (a, b) -> b[0] - a[0]);
            if (count[0][0] > ((len + 1) >> 1)) {
                return "";
            }
//            int i = 0;
//            int k = 0;
            char[] cs = new char[len];
            int k = 0;
            for (int i = 0; i < count.length; i++) {
                int t = count[i][0];
                char c = (char) count[i][1];
                while (t>0){
                    cs[k] = c;
                    k+=2;
                    t--;
                    if (k>=len){
                        k = 1;
                    }
                }
            }

            return new String(cs);


        }
    }
}