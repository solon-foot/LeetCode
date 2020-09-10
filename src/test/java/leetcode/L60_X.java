package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L60_X {
    private interface Solution {
        int getPermutation(int n, int[] arr);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, true);

        assertEquals(4, solution.getPermutation(3,new int[]{2,0,1}));
        assertEquals(5, solution.getPermutation(3,new int[]{2,1,0}));
        assertEquals(8, solution.getPermutation(4,new int[]{1,2,0,3}));
        assertEquals(23, solution.getPermutation(4,new int[]{3,2,1,0}));


    }

    private static class Solution1 implements Solution {
        int[] size = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
        @Override
        public int getPermutation(int n, int[] arr) {
            int ans = 0;
            int len = arr.length;
            for (int i = 0; i < len-1; i++) {
                ans += arr[i] * size[len-i-1];
                for (int j = i+1; j <len-1 ; j++) {
                    if (arr[j]>arr[i])arr[j]--;
                }
            }
            return ans;
        }
    }
//    private static class Solution2 implements Solution {
//        int[] size = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
//        @Override
//        public int getPermutation(int n, int[] arr) {
//
//            int ans = 0;
//            int len = arr.length-1;
//            int i=1;
//            int flag = arr[len];
//            while (len-->0){
//                ans += size[len];
//            }
//            for (int i = 0; i < len; i++) {
//                ans += arr[i] * size[len-i-1];
//                for (int j = i+1; j <len ; j++) {
//                    if (arr[j]>arr[i])arr[j]--;
//                }
//            }
//            return ans;
//        }
//    }
}