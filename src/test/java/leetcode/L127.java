package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class L127 {
    private interface Solution {
        int ladderLength(String beginWord, String endWord, List<String> wordList);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
//        assertEquals(1, solution.ladderLength("hit", "hot", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        assertEquals(5, solution.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
//        assertEquals(0, solution.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }

    private static class Solution2 implements Solution {

        Map<String, Integer> wordId = new HashMap<String, Integer>();
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        int nodeNum = 0;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            for (String word : wordList) {
                addEdge(word);
            }
            addEdge(beginWord);
            if (!wordId.containsKey(endWord)) {
                return 0;
            }
            int[] dis = new int[nodeNum];
            Arrays.fill(dis, Integer.MAX_VALUE);
            int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
            dis[beginId] = 0;

            Queue<Integer> que = new LinkedList<Integer>();
            que.offer(beginId);
            while (!que.isEmpty()) {
                int x = que.poll();
                if (x == endId) {
                    return dis[endId] / 2 + 1;
                }
                for (int it : edge.get(x)) {
                    if (dis[it] == Integer.MAX_VALUE) {
                        dis[it] = dis[x] + 1;
                        que.offer(it);
                    }
                }
            }
            return 0;
        }

        public void addEdge(String word) {
            addWord(word);
            int id1 = wordId.get(word);
            char[] array = word.toCharArray();
            int length = array.length;
            for (int i = 0; i < length; ++i) {
                char tmp = array[i];
                array[i] = '*';
                String newWord = new String(array);
                addWord(newWord);
                int id2 = wordId.get(newWord);
                edge.get(id1).add(id2);
                edge.get(id2).add(id1);
                array[i] = tmp;
            }
        }

        public void addWord(String word) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, nodeNum++);
                edge.add(new ArrayList<Integer>());
            }
        }
    }

    private static class Solution1 implements Solution {

        @Override
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            int startIndex = wordList.indexOf(beginWord);
            int endIndex = wordList.indexOf(endWord);
            if (endIndex < 0) return 0;
            if (startIndex < 0) {
                wordList = new ArrayList<>(wordList);
                wordList.add(beginWord);
                startIndex = wordList.size() - 1;
            }
            int len = wordList.size();
            int[][] arr = new int[len][len];
            for (int i = 0; i < len; i++) {
                arr[i][i] = 0;
                for (int j = i + 1; j < len; j++) {
                    arr[i][j] = arr[j][i] = canChange(wordList.get(i), wordList.get(j)) ? 1 : -1;
                }
            }
//            for (int k = 0; k < len; k++) {
//                for (int i = 0; i < len; i++) {
//                    for (int j = 0; j < len; j++) {
//                        if (e[i][j] > e[i][k] + e[k][j])
//                            e[i][j] = e[i][k] + e[k][j];
//
//                    }
//                }
//            }
            return 0;

//            return dfs(arr, startIndex, endIndex,0);
        }


        boolean canChange(String s1, String s2) {
            boolean flag = true;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    if (flag) flag = false;
                    else return false;
                }

            }
            return true;
        }
    }
}