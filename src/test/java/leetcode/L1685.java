package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L1685 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public int findMagicIndex(int[] nums) {
            for (int i=0;i<nums.length;){
                if (nums[i]==i)return i;
                i = Math.max(i+1,nums[i]);
            }
            return -1;
        }
    }
}