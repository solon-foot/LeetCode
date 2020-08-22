package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 99. 恢复二叉搜索树
 * 二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 *
 * 输入: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 *
 * 输入: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * 进阶:
 *
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 */
public class L99 {
    @Test
    public void test() {
        Solution solution = new Solution();
        assertEquals(true, true);
    }

    private static class Solution {
        public void recoverTree(TreeNode root) {
            TreeNode[] e = new TreeNode[2];
            check(root,Integer.MAX_VALUE,e);
            int t = e[0].val;
            e[0].val = e[1].val;
            e[1].val = t;
        }
        public void check(TreeNode node,int t,TreeNode[] e){

            if (node.left!=null){

                if (node.left.val<t){
                    t = node.left.val;
                } else {
                    if (e[0]==null){
                        e[0] = node.left;
                    } else {
                        e[1]=node.left;
                        return;
                    }
                }
                check(node.left,t,e);

            }
            if (node.val>t){
                t = node.val;
            } else {
                if (e[0]==null){
                    e[0] = node;
                } else {
                    e[1]=node;
                    return;
                }
            }
            if (node.right!=null){

            }
        }
    }
}