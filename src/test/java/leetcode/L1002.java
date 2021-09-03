package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 1002. 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 */
public class L1002 {
    private interface Solution {
        List<String> commonChars(String[] A);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Arrays.asList("e","l","l"), solution.commonChars(new String[]{"bella","label","roller"}));
        assertEquals(Arrays.asList("c","o"), solution.commonChars(new String[]{"cool","lock","cook"}));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<String> commonChars(String[] A) {
            int len = A.length;
            int[][] count = new int[len][26];
            for (int i = 0; i < len; i++) {
                String s = A[i];
                for (int j = 0; j < s.length(); j++) {
                    count[i][s.charAt(j)-'a']++;
                }
            }
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < len; j++) {
                    min = Math.min(count[j][i],min);
                }
                for (int j = 0; j < min; j++) {
                    ans.add(String.valueOf((char)(i+'a')));
                }
            }
            return ans;
        }
    }
}