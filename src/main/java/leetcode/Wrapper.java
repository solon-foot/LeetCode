package leetcode;

import java.util.*;

class Wrapper {


    public static List stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return Collections.EMPTY_LIST;
        }
        List output = new ArrayList();
        int i = 0;
        int count = 0;
        int strCount = 0;
        int from = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '\\') {
                i++;
            } else if (strCount > 0) {
                if (c=='"') strCount=0;
            } else if (c == '"') strCount++;
            else if (c == '[') count++;
            else if (c == ']') count--;
            else if (c == ',' && count == 0) {
                String part = input.substring(from, i).trim();
                if (part.charAt(0) == '[') {
                    output.add(stringToArray(part));
                } else if (part.charAt(0) == '"') {
                    output.add(part.substring(1, part.length() - 1));
                } else {
                    output.add(Integer.parseInt(part));
                }
                from = i + 1;
            }
            i++;
        }
        if (from!=i){
            String part = input.substring(from, i).trim();
            if (part.charAt(0) == '[') {
                output.add(stringToArray(part));
            } else if (part.charAt(0) == '"') {
                output.add(part.substring(1, part.length() - 1));
            } else {
                output.add(Integer.parseInt(part));
            }
        }

        return output;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static void prettyPrintLinkedList(ListNode node) {
        while (node != null && node.next != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }

        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println("Empty LinkedList");
        }
    }
}