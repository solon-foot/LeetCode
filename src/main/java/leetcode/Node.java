package leetcode;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    @Override
    public String toString() {
        Map<Integer,Node> map = new TreeMap<>();
        process(map,this);
        Iterator<Map.Entry<Integer, Node>> iterator = map.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        if (!iterator.hasNext()) {
            sb.append("[]");
        } else {
            sb.append('[');
            for (; ; ) {
                Map.Entry<Integer, Node> e = iterator.next();
                Node value = e.getValue();
                Iterator<Node> it = value.neighbors.iterator();
                if (!it.hasNext()){
                    sb.append("[]");
                } else {
                    sb.append('[');
                    for (;;){
                        Node next = it.next();
                        sb.append(next.val);
                        if (!it.hasNext()){
                            sb.append(']');
                            break;
                        }
                        sb.append(',').append(' ');
                    }
                }
                if (!iterator.hasNext()){
                    sb.append(']');
                    break;
                }
                sb.append(',').append(' ');
            }
        }
        return sb.toString();
    }
    private void process(Map<Integer,Node> map,Node node){
        if (map.containsKey(node.val))return;
        map.put(node.val,node);
        if (node.neighbors==null||node.neighbors.isEmpty())return;
        for (Node neighbor : node.neighbors) {
            process(map,neighbor);
        }
    }

    public static Node create(String string) {
        return create(Wrapper.stringToArray(string));
    }
    private static Node create(List<List<Integer>> items){
        if (items.isEmpty())return null;
        Node[]nodes = new Node[items.size()];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i+1);
        }
        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];
            for (Integer t : items.get(i)) {
                node.neighbors.add(nodes[t-1]);
            }
        }
        return nodes[0];
    }
}