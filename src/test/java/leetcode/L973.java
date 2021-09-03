package leetcode;

import javafx.collections.transformation.SortedList;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 973. 最接近原点的 K 个点
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * <p>
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class L973 {
    private interface Solution {
        int[][] kClosest(int[][] points, int K);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertArrayEquals(new int[][]{{-2, 2}}, solution.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1));
//        assertArrayEquals(new int[][]{{3, 3},{-2, 4}}, solution.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));
//        assertArrayEquals(new int[][]{ {2, -2},{-2, 2}}, solution.kClosest(new int[][]{{1, 3}, {-2, 2}, {2, -2}}, 2));
        assertArrayEquals(new int[][]{{-2, 4}, {3, 3}}, solution.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));
        assertArrayEquals(new int[][]{{-2, 2}, {2, -2}}, solution.kClosest(new int[][]{{1, 3}, {-2, 2}, {2, -2}}, 2));
    }

    class Solution2 implements Solution {
        public int[][] kClosest(int[][] points, int K) {
            if (points == null || points.length == 0) return new int[0][];
            if (K >= points.length) return points;
            int left = 0, right = points.length - 1, pos = -1;
            while (pos != K - 1) {
                pos = partition(points, left, right);
                if (pos < K - 1) left = pos + 1;
                if (pos > K - 1) right = pos - 1;
            }
            return Arrays.copyOf(points, K);
        }

        public int distance(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }

        public int partition(int[][] points, int left, int right) {
            int dis = distance(points[left]);
            while (left < right) {
                while (left < right && (distance(points[right])) >= dis) right--;
                if (left < right) swap(points, left, right);
                while (left < right && (distance(points[left])) <= dis) left++;
                if (left < right) swap(points, left, right);
            }
            swap(points, left, right);
            return left;
        }

        public void swap(int[][] points, int left, int right) {
            int[] tmp = points[left];
            points[left] = points[right];
            points[right] = tmp;
        }
    }

    private static class Solution1 implements Solution {

        @Override
        public int[][] kClosest(int[][] points, int K) {
            int len = points.length;
            if (len == K) return points;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a2[0] - a1[0]);
            for (int i = 0; i < K; ++i)
                pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});

            for (int i = K; i < len; ++i) {
                int dis = points[i][0] * points[i][0] + points[i][1] * points[i][1];
                if (pq.peek()[0] > dis) {
                    int[] val = pq.poll();
                    val[0] = dis;
                    val[1] = i;
                    pq.offer(val);
                }
            }

            int[][] ans = new int[K][2];
            for (int i = 0; i < K; ++i)
                ans[i] = points[pq.poll()[1]];

            return ans;
        }
    }
}