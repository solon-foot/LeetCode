package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class L60 {
    private interface Solution {
        String getPermutation(int n, int k);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals("1", solution.getPermutation(1,1));
        assertEquals("12", solution.getPermutation(2,1));
        assertEquals("21", solution.getPermutation(2,2));
        assertEquals("123", solution.getPermutation(3,1));
        assertEquals("132", solution.getPermutation(3,2));
        assertEquals("213", solution.getPermutation(3,3));
        assertEquals("231", solution.getPermutation(3,4));
        assertEquals("312", solution.getPermutation(3,5));
        assertEquals("321", solution.getPermutation(3,6));

        assertEquals("2314", solution.getPermutation(4,9));
    }

    private static class Solution1 implements Solution {
        int[] size = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
        @Override
        public String getPermutation(int n, int k) {

            StringBuilder  sb = new StringBuilder();
            --k;
            List<Integer> list = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) list.add(i);
            while (n-->0){
                sb.append(list.remove(k/(size[n])));
                k %=(size[n]);
            }

            return sb.toString();
        }
    }
    private static class Solution2 implements Solution {
        public String getPermutation(int n, int k) {
            int[] factorial = new int[n];
            factorial[0] = 1;
            for (int i = 1; i < n; ++i) {
                factorial[i] = factorial[i - 1] * i;
            }

            --k;
            StringBuffer ans = new StringBuffer();
            int[] valid = new int[n + 1];
            Arrays.fill(valid, 1);
            for (int i = 1; i <= n; ++i) {
                int order = k / factorial[n - i] + 1;
                for (int j = 1; j <= n; ++j) {
                    order -= valid[j];
                    if (order == 0) {
                        ans.append(j);
                        valid[j] = 0;
                        break;
                    }
                }
                k %= factorial[n - i];
            }
            return ans.toString();
        }
    }

}