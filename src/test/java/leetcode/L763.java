package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 */
public class L763 {
    private interface Solution {
        List<Integer> partitionLabels(String S);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Arrays.asList(9,7,8), solution.partitionLabels("ababcbacadefegdehijhklij"));
        assertEquals(Arrays.asList(1), solution.partitionLabels("a"));
        assertEquals(Arrays.asList(2), solution.partitionLabels("aa"));
        assertEquals(Arrays.asList(1,1), solution.partitionLabels("ab"));

    }

    private static class Solution1 implements Solution {

        @Override
        public List<Integer> partitionLabels(String S) {
            int[] a1 = new int[26];
            for (int i = 0; i < S.length(); i++) {
                a1[S.charAt(i)-'a'] = i+1;
            }
            int from=0;
            int end =0;
            int i=0;
            List<Integer>ans = new ArrayList<>();
            while (i<S.length()) {
                 do {
                     end= Math.max(end,a1[S.charAt(i++)-'a']);
                 }while (i<end && i<S.length());
                 ans.add(end-from);
                 from = end;
            }
            return ans;
        }
    }
}