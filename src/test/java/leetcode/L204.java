package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= n <= 5 * 106
 */
public class L204 {
    private interface Solution {
        int countPrimes(int n);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(4, solution.countPrimes(10));
        assertEquals(0, solution.countPrimes(0));
        assertEquals(0, solution.countPrimes(1));
        assertEquals(0, solution.countPrimes(2));
        assertEquals(1, solution.countPrimes(3));
        long t = System.nanoTime();
        TLog.e(solution.countPrimes(5000_000));
        TLog.e(Math.sqrt(Long.MAX_VALUE),Integer.MAX_VALUE);;
        TLog.e(solution.countPrimes((int) 2e9));
        t = System.nanoTime()-t;
        t/=1000_000;
        TLog.e(t);
    }
    private static class Solution1 implements Solution {

        @Override
        public int countPrimes(int n) {
            List<Integer> primes = new ArrayList<Integer>();
            boolean[] isPrime = new boolean[n];
            Arrays.fill(isPrime, true);
            for (int i = 2; i < n; ++i) {
                if (isPrime[i]) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                    isPrime[i * primes.get(j)] = false;
                    if (i % primes.get(j) == 0) {
                        break;
                    }
                }
            }
            return primes.size();
        }
    }
    private static class Solution2 implements Solution {

        @Override
        public int countPrimes(int n) {
            boolean[] isPrime = new boolean[n];
            Arrays.fill(isPrime, true);
            int ans = 0;
            for (int i = 2; i < n; ++i) {
                if (isPrime[i]) {
                    ans += 1;
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            isPrime[j] = false;
                        }
                    }
                }
            }
            return ans;
        }
    }
    private static class Solution3 implements Solution {

        @Override
        public int countPrimes(int n) {
            if (n<3)return 0;
             count = 1;
             head = new Node(2);
            last = head;
            for (int i = 3; i < n; i+=2) {
                if (isPrimes(i)){
                    last = last.next = new Node(i);
                    count++;
                }
            }
            return count;
        }
        public boolean isPrimes(int t){
//            int t2 = (int) Math.sqrt(t);
            Node cursor = head;
            while (cursor!=null &&cursor.val*cursor.val<=t){
                if (t%cursor.val==0)return false;
                cursor = cursor.next;
            }
            return true;
        }
        int count ;
        Node head;
        Node last;
        class Node{
            int val;
            Node next;

            Node(int val) {
                this.val = val;
            }
        }
    }
}