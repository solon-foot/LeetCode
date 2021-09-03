package leetcode;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 *
 */
public class L645 {
    private interface Solution {
        String serialize(TreeNode root);
        TreeNode deserialize(String data);
    }

    @Test
    public void test() {
//        Solution solution = new Solution1();
//        assertEquals(true, solution.deserialize("[1,2,3,null,null,4,5]"));
        Codec codec = new Codec();
//        String serialize = codec.serialize(TreeNode.create(1, 2, 3, null, null, 4, 5));
        String serialize = codec.serialize(TreeNode.create(1, 2, 3));
        System.out.println(serialize);
        System.out.println(codec.deserialize(serialize));

    }

    private static class Solution1 implements Solution {
        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode rdeserialize(List<String> l) {
            if (l.get(0).equals("null")) {
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            l.remove(0);
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }

        public TreeNode deserialize(String data) {
            data = data.substring(1,data.length()-1);
            String[] data_array = data.split(",");
            List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }
    }
    public class Codec {
        public String rserialize(TreeNode root, String str) {
            if (root == null) {
                str += "None,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode rdeserialize(List<String> l) {
            if (l.get(0).equals("None")) {
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            l.remove(0);
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }

        public TreeNode deserialize(String data) {
            String[] data_array = data.split(",");
            List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }
    }

}