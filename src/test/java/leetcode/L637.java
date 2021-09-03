package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 */
public class L637 {
    private interface Solution {
        List<Double> averageOfLevels(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(Arrays.asList(3d,14.5d,11d), solution.averageOfLevels(TreeNode.create(3,9,20,null,null,15,7)));
    }

    private static class Solution1 implements Solution {

        @Override
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> ans = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int size = queue.size();
            while (size != 0) {
                double sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    sum += node.val;
                    if (node.left != null)
                        queue.offer(node.left);
                    if (node.right != null)
                        queue.offer(node.right);
                }
                ans.add(sum / size);
                size = queue.size();
            }
            return ans;
        }
    }
}