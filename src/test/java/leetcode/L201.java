package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L201 {
    private interface Solution {
        int rangeBitwiseAnd(int m, int n);
    }

    @Test
    public void test() {
        int n = 0b111100;
        n = n&(n-1);
        System.out.println(Integer.toBinaryString(n));
        Solution solution = new Solution2();
        assertEquals(4, solution.rangeBitwiseAnd(5, 7));
        assertEquals(0, solution.rangeBitwiseAnd(0, 1));
        assertEquals(4, solution.rangeBitwiseAnd(4, 7));
        assertEquals(0, solution.rangeBitwiseAnd(0, 2147483647));
        Solution solution1 = new Solution3();
        for (int i = 0; i < 513; i++) {
            for (int j = i; j < 513; j++) {
                assertEquals(solution1.rangeBitwiseAnd(i, j), solution.rangeBitwiseAnd(i, j));
            }
        }
    }

    private static class Solution1 implements Solution {

        @Override
        public int rangeBitwiseAnd(int m, int n) {
            int ans = n;
            for (int i = m; i < n; i++) {
                ans &= i;
            }
            return ans;
        }
    }

    private static class Solution2 implements Solution {

        @Override
        public int rangeBitwiseAnd(int m, int n) {
            int i = 0x8000_0000;
            for (; i!=0; i>>>=1) {
                if ((n&i)!=0){
                    if ((m&i)==0){
                        return 0;
                    }
                    i>>>=1;
                    break;
                }
            }
            for (; i!=0; i>>>=1) {
                if ((m&i)!=0){
                    if ((n&~i)>m){
                        m &= ~i;
                    }
                }
            }
            return m;
        }
    }
    class Solution3 implements Solution {
        public int rangeBitwiseAnd(int m, int n) {
            int i = 0;
            for (; m <n ; i++) {
                m>>=1;
                n>>=1;
            }
            return m<<i;
        }
    }
    class Solution4 implements Solution {
        public int rangeBitwiseAnd(int m, int n) {
            while (m < n) n &= (n - 1);    // 抹去最右边的 1
            return n;
        }
    }
}