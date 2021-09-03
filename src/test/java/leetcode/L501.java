package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * <p>
 * 1
 * \
 * 2
 * /
 * 2
 * 返回[2].
 * <p>
 * 提示：如果众数超过1个，不需考虑输出顺序
 * <p>
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class L501 {
    private interface Solution {
        int[] findMode(TreeNode root);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertArrayEquals(new int[]{2}, solution.findMode(TreeNode.create(1, null, 2, 2)));
        assertArrayEquals(new int[]{1, 2}, solution.findMode(TreeNode.create(1, null, 2)));
    }

    private static class Solution1 implements Solution {

        @Override
        public int[] findMode(TreeNode root) {
            max = 0;
            last = null;
            list = new ArrayList<>();
            count = 0;
            dfs(root);
            log();
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }

            return res;
        }

        int max = -1;
        int count;
        Integer last;
        List<Integer> list;

        void log() {
            if (count > max) {
                max = count;
                list.clear();
                list.add(last);
            } else if (max == count) {
                if (last != null)
                    list.add(last);
            }
        }

        void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.left);
            if (last != null && last == root.val) {
                count++;
            } else {
                log();
                count = 0;
                last = root.val;
            }
            dfs(root.right);

        }
    }

    private static class Solution2 implements Solution {

        @Override
        public int[] findMode(TreeNode root) {
            max = 0;
            last = null;
            list = new ArrayList<>();
            dfs(root);
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }

            return res;
        }

        int max;
        int count;
        Integer last;
        List<Integer> list;

        void dfs(TreeNode root) {
            if (root == null) return;
            dfs(root.left);
            if (last != null && last == root.val) {
                count++;
            } else {
                count = 1;
                last = root.val;
            }
            if (count > max) {
                max = count;
                list.clear();
                list.add(last);
            } else if (max == count) {
                list.add(last);
            }
            dfs(root.right);

        }
    }
}