package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * L207 课程表
 * 207. 课程表
 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

  

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/course-schedule
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class L207 {
    @Test
    public void test() {
        Solution solution = new Solution();
        long t = System.currentTimeMillis();
        assertEquals(solution.canFinish(2_000000,new int[][]{{1,0}}), true);
        assertEquals(solution.canFinish(2_000000,new int[][]{{1,0},{0,1}}), false);
        System.out.println(System.currentTimeMillis() - t);
    }

    private static class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            boolean[] bs = new boolean[numCourses];
//            Set<Integer>[] cursesSet = new Set[numCourses];
            HashMap<Integer,Set<Integer>> cursesSet = new HashMap<>();
            for (int[] prerequisite : prerequisites) {
                Set<Integer> set= cursesSet.get(prerequisite[0]);
                if (set == null){
                    set = new HashSet<>();
                    cursesSet.put(prerequisite[0],set);
                }
                set.add(prerequisite[1]);
            }

            boolean flag = true;
            do{
                flag = false;
                int k = 0;
                for (int i = 0; i < numCourses; i++) {
                    if (bs[i]){
                        k++;
                    } else{
                        Set<Integer> set = cursesSet.get(i);
                        if (set==null||set.removeIf(integer -> bs[integer]) && set.isEmpty()){
                            bs[i]=true;
                            k++;
                            flag = true;
//                            cursesSet[i] = null;
                        }
                    }

                }
                if (k == numCourses)return true;

            } while (flag);
        return false;
        }
        public boolean canFinish2(int numCourses, int[][] prerequisites) {
            boolean[] bs = new boolean[numCourses];
            Set<Integer>[] cursesSet = new Set[numCourses];

            for (int[] prerequisite : prerequisites) {
                int curse = prerequisite[0];
                Set<Integer> set= cursesSet[prerequisite[0]];
                if (set == null){
                    set = new HashSet<>();
                    cursesSet[prerequisite[0]] = set;
                }
                set.add(prerequisite[1]);
            }

            boolean flag = true;
            do{
                flag = false;
                int k = 0;
                for (int i = 0; i < numCourses; i++) {
                    if (bs[i]){
                        k++;
                    } else{
                        Set<Integer> set = cursesSet[i];
                        if (set==null||set.removeIf(integer -> bs[integer]) && set.isEmpty()){
                            bs[i]=true;
                            k++;
                            flag = true;
//                            cursesSet[i] = null;
                        }
                    }

                }
                if (k == numCourses)return true;

            } while (flag);
        return false;
        }
    }
}