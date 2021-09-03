package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 844. 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 * <p>
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 * <p>
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 * <p>
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 */
public class L844 {
    private interface Solution {
        boolean backspaceCompare(String S, String T);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, solution.backspaceCompare("ab#c", "ad#c"));
        assertEquals(true, solution.backspaceCompare("ab##", "c#d#"));
        assertEquals(true, solution.backspaceCompare("a##c", "#a#c"));
        assertEquals(false, solution.backspaceCompare("a#c", "b"));
        assertEquals(true, solution.backspaceCompare("bxj##tw", "bxo#j##tw"));
        assertEquals(true, solution.backspaceCompare("nzp#o#g", "b#nzp#o#g"));
    }

    private static class Solution1 implements Solution {

        @Override
        public boolean backspaceCompare(String S, String T) {
            int i = S.length() - 1;
            int j = T.length() - 1;
            while (i >= 0 && j >= 0) {
                i = back(i, S);
                j = back(j, T);
                if (i < 0 || j < 0) break;
                if (S.charAt(i--) != T.charAt(j--)) return false;
            }
            i = back(i, S);
            j = back(j, T);

            return i < 0 && j < 0;
        }

        int back(int i, String s) {
            int skip = 0;
            while (i >= 0) {
                if (s.charAt(i) == '#') skip++;
                else if (skip > 0) skip--;
                else break;
                i--;
            }
            return i;
        }
//        int back(int i,String s) {
//            int skip = 0;
//            while (i>=0&&s.charAt(i)=='#') {
//                skip++;
//                i--;
//                while (i>=0&&s.charAt(i)=='#'){skip++;i--;}
//                while (i>=0&&skip>0&&s.charAt(i)!='#'){skip--;i--;}
//            }
//            return i;
//        }
    }
}