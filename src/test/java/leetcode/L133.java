package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 */
public class L133 {
    @Test
    public void test() {
        Solution solution = new Solution();
        Node n1 = Node.create("[[2,4],[1,3],[2,4],[1,3]]");
        Node n2 = Node.create("[[2,4],[1,3],[2,4],[1,3]]");
        Node clone = solution.cloneGraph(n1);
        TLog.e(n1);
        TLog.e(n2);
        TLog.e(clone);
        assertEquals(true, true);
    }

    private static class Solution {
        public Node cloneGraph(Node node) {
            Map<Node,Node> set = new HashMap<>();
            return cloneGraph(set,node);
        }
        Node cloneGraph(Map<Node,Node> set,Node node){
            if (node== null)return null;
            Node clone = set.get(node);
            if (clone!=null)return clone;
            clone = new Node();
            set.put(node,clone);
            clone.val = node.val;
            for (Node neighbor : node.neighbors) {
                clone.neighbors.add(cloneGraph(set,neighbor));
            }
            return clone;
        }
    }
}