package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 */
public class L491 {
    private interface Solution {
        List<List<Integer>> findSubsequences(int[] nums);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Wrapper.stringToArray("[[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]"), solution.findSubsequences(new int[]{10,4, 6, 7, 7}));
        assertEquals(Wrapper.stringToArray("[[4, 6], [4, 7],[4, 8], [4, 6, 7], [4, 6, 8], [4, 6, 7, 8], [6, 7], [6, 8], [6, 7, 8], [7,8], [4,7,8]]"), solution.findSubsequences(new int[]{4, 6, 7, 8}));
    }

    private static class Solution1 implements Solution {

        Set<List<Integer>> set = new HashSet<>();

        @Override
        public List<List<Integer>> findSubsequences(int[] nums) {
//            Arrays.sort(nums);
            set.clear();
            List<List<Integer>> ans = new ArrayList<>();
            int len = nums.length;
            for (int i = 0; i < 1 << len; i++) {
                List<Integer> integers = new ArrayList<>();
                int last = -200;
                for (int j = 0; j < len; j++) {
                    if ((i & (1 << j)) != 0) {
                        if (nums[j]<last){
                            break;

                        }
                        last = nums[j];
                        integers.add(last);
                    }
                }

                if (integers.size() > 1&&!set.contains(integers)){
                    ans.add(integers);
                    set.add(integers);
                }
            }
            return ans;
        }
    }
}