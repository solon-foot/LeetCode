package leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 */
public class L117 {
    private interface Solution {
        Node connect(Node root);
    }

    @Test
    public void test() {
        Solution solution = new Solution1();
        assertEquals(true, solution.connect(new Node(1, new Node(2, new Node(4), new Node(5)), new Node(3, null, new Node(7)))));
    }

    private static class Solution1 implements Solution {

        @Override
        public Node connect(Node root) {
            if (root == null) return null;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            int size;
            while ((size = queue.size()) != 0) {
                Node pre = queue.poll();
                if (pre.left != null) queue.offer(pre.left);
                if (pre.right != null) queue.offer(pre.right);
                Node next;
                for (int i = 1; i < size; i++) {
                    next = queue.poll();
                    pre.next = next;
                    pre = next;
                    if (pre.left != null) queue.offer(pre.left);
                    if (pre.right != null) queue.offer(pre.right);
                }

            }
            return root;
        }

    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        @Override
        public String toString() {
//            StringBuilder sb = new StringBuilder();
//            Queue<Node> queue = new LinkedList<>();
//            queue.offer(this);
//            Node pre = null;
//            while (queue.isEmpty()) {
//                Node node = queue.poll();
//                if (pre!=null){
//                    sb.append(pre.val)
//                }
//                pre = queue.poll();
//
//            }
            return super.toString();
        }
    }

}