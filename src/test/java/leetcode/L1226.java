package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class L1226 {
    private interface Solution {

    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, true);
    }

    private static class Solution1 implements Solution {

    }

    static class DiningPhilosophers {

        public DiningPhilosophers() {
            for (int i = 0; i < objects.length; i++) {
                objects[i] = new Object();
            }
        }
        Object[] objects = new Object[5];

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            Object left = objects[philosopher];
            Object right = objects[(philosopher+1)%5];
            if ((philosopher & 1) == 1) {
                synchronized (left){
                    pickLeftFork.run();
                    synchronized (right){
                        pickRightFork.run();
                        eat.run();
                        putRightFork.run();
                    }
                    putLeftFork.run();

                }

            } else {
                synchronized (right){
                    pickRightFork.run();
                    synchronized (left){
                        pickLeftFork.run();

                        eat.run();
                        putLeftFork.run();
                    }
                    putRightFork.run();
                }

            }


        }
    }
}