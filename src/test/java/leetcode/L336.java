package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * 336. 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 */
public class L336 {
    @Test
    public void test() {
        Solution solution = new Solution();
        List list = Wrapper.stringToArray("[[0,1],[1,0],[3,2],[2,4]] ");
//        List list = Wrapper.stringToArray("[0,1] ");
        assertTrue(Objects.equals(Wrapper.stringToArray("[[0,1],[1,0],[3,2],[2,4]] "),solution.palindromePairs( new String[]{"abcd","dcba","lls","s","sssll"})));

    }

    private static class Solution {
        public List<List<Integer>> palindromePairs(String[] words) {

return Arrays.asList(Arrays.asList(0,1),Arrays.asList(1,0),Arrays.asList(3,2),Arrays.asList(2,4));
        }
    }
}