package leetcode;

import javafx.collections.transformation.SortedList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class L57 {
    private interface Solution {
        int[][] insert(int[][] intervals, int[] newInterval);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertArrayEquals(new int[][]{{1,5},{6,9}}, solution.insert(new int[][]{{1,3},{6,9}},new int[]{2,5}));
        assertArrayEquals(new int[][]{{1,3},{4,9}}, solution.insert(new int[][]{{1,3},{6,9}},new int[]{4,7}));

        assertArrayEquals(new int[][]{{-5,0},{1,10},{20,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{-5,0}));
        assertArrayEquals(new int[][]{{0,10},{20,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,1}));
        assertArrayEquals(new int[][]{{0,10},{20,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,5}));
        assertArrayEquals(new int[][]{{0,10},{20,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,10}));
        assertArrayEquals(new int[][]{{0, 15}, {20, 30}, {40, 50}}, solution.insert(new int[][]{{1, 10}, {20, 30}, {40, 50}}, new int[]{0, 15}));
        assertArrayEquals(new int[][]{{0,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,20}));
        assertArrayEquals(new int[][]{{0,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,25}));
        assertArrayEquals(new int[][]{{0,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,30}));
        assertArrayEquals(new int[][]{{0,35},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,35}));
        assertArrayEquals(new int[][]{{0,55}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{0,55}));
        assertArrayEquals(new int[][]{{1,3},{6,9}}, solution.insert(new int[][]{{1,3},{6,9}},new int[]{7,8}));
        assertArrayEquals(new int[][]{{1,3},{4,5},{6,9}}, solution.insert(new int[][]{{1,3},{6,9}},new int[]{4,5}));
        assertArrayEquals(new int[][]{{1,2},{3,10},{12,16}}, solution.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}},new int[]{4,8}));



        assertArrayEquals(new int[][]{{1,10},{20,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{4,5}));
        assertArrayEquals(new int[][]{{1,10},{20,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{5,10}));
        assertArrayEquals(new int[][]{{1, 15}, {20, 30}, {40, 50}}, solution.insert(new int[][]{{1, 10}, {20, 30}, {40, 50}}, new int[]{5, 15}));
        assertArrayEquals(new int[][]{{1,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{5,20}));
        assertArrayEquals(new int[][]{{1,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{5,25}));
        assertArrayEquals(new int[][]{{1,30},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{5,30}));
        assertArrayEquals(new int[][]{{1,35},{40,50}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{5,35}));
        assertArrayEquals(new int[][]{{1,55}}, solution.insert(new int[][]{{1,10},{20,30},{40,50}},new int[]{5,55}));


    }

    private static class Solution2 implements Solution {

        @Override
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int left = newInterval[0];
            int right = newInterval[1];
            boolean placed = false;
            List<int[]> ansList = new ArrayList<>();
            for (int[] interval : intervals) {
                if (interval[0] > right) {
                    if (!placed) {
                        ansList.add(new int[]{left, right});
                        placed = true;
                    }
                    ansList.add(interval);
                } else if (interval[1] < left) {
                    ansList.add(interval);
                } else {
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                }
            }
            if (!placed) {
                ansList.add(new int[]{left, right});
            }
            return ansList.toArray(new int[ansList.size()][2]);
        }
    }
//    private static class Solution1 implements Solution {
//
//        @Override
//        public int[][] insert(int[][] intervals, int[] newInterval) {
//            int len = intervals.length;
//            int i = binarySearch0(intervals, 0, len, newInterval[0]);
//            int j;
//            if (i >= 0) {
//                j = binarySearch0(intervals, i, len, newInterval[1]);
//            } else {
//                j = binarySearch0(intervals, ~i, len, newInterval[1]);
//            }
//            TLog.e(i, j, ~i, ~j);
//            if (i > 0 && j > 0) {
////                if (i==j)return intervals;
//                int[][] ans = new int[len - j + i][];
//                System.arraycopy(intervals, 0, ans, 0, i);
//                System.arraycopy(intervals, j, ans, i, len - j);
//                ans[i][0] = intervals[i][0];
//                return ans;
//            } else if (i < 0 && j < 0) {
//                i = ~i;
//                j = ~j;
//                int[][] ans = new int[len - j + i + 1][];
//                System.arraycopy(intervals, 0, ans, 0, i);
//                System.arraycopy(intervals, j, ans, i + 1, len - j);
//                ans[i] = newInterval;
//                return ans;
//            } else if (i < 0) {
//                i = ~i;
//                if (i == j) {
//                    intervals[i][0] = newInterval[0];
//                    return intervals;
//                }
//                int[][] ans = new int[len - j + i][];
//                System.arraycopy(intervals, 0, ans, 0, i+1);
//                System.arraycopy(intervals, j, ans, i , len - j);
//                ans[i][0] = newInterval[0];
//                TLog.e(ans);
//                return ans;
//            } else {
//                j = ~j;
//                int[][] ans = new int[len - j + i + 1][];
//                System.arraycopy(intervals, 0, ans, 0, i + 1);
//                System.arraycopy(intervals, j, ans, i + 1, len - j);
//                ans[i][1] = newInterval[1];
//                TLog.e(ans);
//                return ans;
//            }
////            return new int[0][];
//        }
//
//        private static int binarySearch0(int[][] a, int fromIndex, int toIndex,
//                                         int key) {
//            int low = fromIndex;
//            int high = toIndex - 1;
//
//            while (low <= high) {
//                int mid = (low + high) >>> 1;
//                int[] midVal = a[mid];
//
//                if (midVal[1] < key)
//                    low = mid + 1;
//                else if (midVal[0] > key)
//                    high = mid - 1;
//                else
//                    return mid; // key found
//            }
//            return -(low + 1);  // key not found.
//        }
//
//    }
}