package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 659. 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *
 *
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 *
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *
 *
 * 提示：
 *
 * 输入的数组长度范围为 [1, 10000]
 */
public class L659 {
    private interface Solution {
        boolean isPossible(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, solution.isPossible(new int[]{1,2,3,3,4,5}));
        assertEquals(true, solution.isPossible(new int[]{1,2,3,3,4,4,5,5}));
        assertEquals(false, solution.isPossible(new int[]{1,2,3,4,4,5}));
    }

    private static class Solution1 implements Solution {
        public boolean isPossible(int[] nums) {
            Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
            Map<Integer, Integer> endMap = new HashMap<Integer, Integer>();
            for (int x : nums) {
                int count = countMap.getOrDefault(x, 0) + 1;
                countMap.put(x, count);
            }
            for (int x : nums) {
                int count = countMap.getOrDefault(x, 0);
                if (count > 0) {
                    int prevEndCount = endMap.getOrDefault(x - 1, 0);
                    if (prevEndCount > 0) {
                        countMap.put(x, count - 1);
                        endMap.put(x - 1, prevEndCount - 1);
                        endMap.put(x, endMap.getOrDefault(x, 0) + 1);
                    } else {
                        int count1 = countMap.getOrDefault(x + 1, 0);
                        int count2 = countMap.getOrDefault(x + 2, 0);
                        if (count1 > 0 && count2 > 0) {
                            countMap.put(x, count - 1);
                            countMap.put(x + 1, count1 - 1);
                            countMap.put(x + 2, count2 - 1);
                            endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                        } else {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }
}