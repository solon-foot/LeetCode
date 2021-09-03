package leetcode;

import java.util.*;

public class TreeNode implements Cloneable {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TreeNode))return false;
        return equals(this, (TreeNode) obj);
    }
    public boolean equals(TreeNode t1,TreeNode t2) {
        if (t1==null && t2 == null)return true;
        if (t1==null || t2 == null||t1.val!=t2.val)return false;
        return equals(t1.left,t2.left)&&equals(t1.right,t2.right);
    }

    public List<Integer> postorderTraversal() {
        List<Integer> ans = new ArrayList<>();
        new Object(){
            void postorderTraversal(TreeNode root) {
                if (root == null)return;
                postorderTraversal(root.left);
                postorderTraversal(root.right);
                ans.add(root.val);
            }
        }.postorderTraversal(this);
        return ans;
    }
    public List<Integer> preorderTraversal() {
        List<Integer> ans = new ArrayList<>();
        new Object(){
            void postorderTraversal(TreeNode root) {
                if (root == null)return;
                ans.add(root.val);
                postorderTraversal(root.left);
                postorderTraversal(root.right);
            }
        }.postorderTraversal(this);
        return ans;
    }
    public List<Integer> midorderTraversal() {
        List<Integer> ans = new ArrayList<>();
        new Object(){
            void postorderTraversal(TreeNode root) {
                if (root == null)return;
                postorderTraversal(root.left);
                ans.add(root.val);
                postorderTraversal(root.right);
            }
        }.postorderTraversal(this);
        return ans;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append('[');
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(this);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output.append("null").append(',');
                continue;
            }
            output.append(node.val).append(',');
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        output.setLength(output.length()  -1  );
        output.append(']');
        return output.toString();
    }
    public static TreeNode create(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static TreeNode create(Integer... list) {
        TreeNode root = new TreeNode(list[0]);
        List<TreeNode> arr = Arrays.asList(root);
        int index = 1;
        while (!arr.isEmpty()) {
            List<TreeNode> arr2 = new ArrayList<>();
            for (TreeNode treeNode : arr) {
                if (index >= list.length) return root;
                Integer t = list[index++];
                if (t != null) {
                    treeNode.left = new TreeNode(t);
                    arr2.add(treeNode.left);
                }
                if (index >= list.length) return root;
                t = list[index++];
                if (t != null) {
                    treeNode.right = new TreeNode(t);
                    arr2.add(treeNode.right);
                }
            }
            arr = arr2;
        }


        return root;
    }

    /**
     *
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * 返回 false 。
     *
     *         4
     *      ┏━━┻━━┓
     *      3     6
     *   ┏━━┻━━┓
     */
    public String prettyString() {
        int height = height(this);
        StringBuilder sb = new StringBuilder();
        //BFS 广度优先遍历
       
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(this);
        while (height>0){
            height--;
            int size = nodeQueue.size();
            int mid = 1<<height;
            for (int i = 0; i < mid; i++) {
                sb.append(' ').append(' ').append(' ');
                sb.append(' ').append(' ').append(' ');
            }
            for (int j = 0; j < size; j++) {
                TreeNode node = nodeQueue.poll();


                if (node==null){
                    sb.append(' ');
                }else {
                    sb.append(node.val);
                }
                for (int i = 0; i < mid*2; i++) {
                    sb.append(' ').append(' ').append(' ');
                    sb.append(' ').append(' ').append(' ');
                }
                sb.append(' ');
//                for (int i = 0; i < leftWidth-1; i++) {
//                    sb.append(' ').append(' ').append(' ');
//                }
//                sb.append("┏━━┻━━┓");
                if (node!=null){

                    nodeQueue.add(node.left);
                    nodeQueue.add(node.right);
                }
            }
            sb.append('\n');
//            mid>>=1;
            for (int i = 0; i < mid; i++) {
                sb.append(' ').append(' ').append(' ');
            }

            for (int j = 0; j < size; j++) {
                sb.append('┏');
                for (int i = 0; i < mid; i++) {
                    sb.append('━').append('━').append('━');
                }
                sb.append('┻');
                for (int i = 0; i < mid; i++) {
                    sb.append('━').append('━').append('━');
                }
                sb.append('┓');
                for (int i = 0; i < mid; i++) {
//                    sb.append(' ').append(' ').append(' ');
                    sb.append(' ').append(' ').append(' ');
                    sb.append(' ').append(' ').append(' ');
                }

            }
            sb.append('\n');

        }
        return sb.toString();
    }
    public int height(TreeNode root){
        if (root == null)return 0;
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left,right)+1;
    }
    public static void main(String[] args) {
        TreeNode node = TreeNode.create("[1,2,2,3,3,null,null,4,4]");
        System.out.println(node.prettyString());
    }

    private void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }
    public void prettyPrintTree( String prefix, boolean isLeft) {
        prettyPrintTree(this,prefix,isLeft);
    }
    public void prettyPrintTree() {
        prettyPrintTree( this,"", true);
    }


}
