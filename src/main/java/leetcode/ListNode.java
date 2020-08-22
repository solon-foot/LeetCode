package leetcode;

public class ListNode implements Cloneable {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }
    public ListNode() {
    }

    public static ListNode create(int... list) {
        ListNode head = new ListNode(list[0]);
        ListNode temp = head;
        for (int i = 1; i < list.length; i++) {
            temp = temp.next = new ListNode(list[i]);

        }
        return head;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        ListNode l = next;
        while (l != null) {
            sb.append("->").append(l.val);
            l = l.next;
        }
        return sb.toString();
    }
    public static ListNode create(String input) {
        // Generate array from the input
        int[] nodeValues = Wrapper.stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    @Override
    protected ListNode clone() {
        ListNode root = new ListNode(this.val);
        ListNode curson = root;
        ListNode next = this.next;
        while (next!=null){
            curson = curson.next = new ListNode(next.val);
            next = next.next;
        }
        return root;
    }
}