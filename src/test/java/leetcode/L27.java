package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L27 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int removeElement(int[] nums, int val) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i]!=val) nums[index++]= nums[i];
            }
            return index;
        }
    }
}