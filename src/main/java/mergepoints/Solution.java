package mergepoints;

import java.util.LinkedList;

public class Solution {

    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode auxhead1 = head1;
        SinglyLinkedListNode auxhead2 = head2;
        int auxhead1Count = 0;
        int auxhead2Count = 0;

        while (auxhead1.next != null) {
            auxhead1Count++;
            auxhead1 = auxhead1.next;
        }

        while (auxhead2.next != null) {
            auxhead2Count++;
            auxhead2 = auxhead2.next;
        }
        int difference;
        SinglyLinkedListNode result = null;
        if (auxhead1Count > auxhead2Count) {
            difference = auxhead1Count - auxhead2Count;
            result = advance(head2, difference);
            System.out.println(result.data);
            return result.data;

        }
        if (auxhead1Count < auxhead2Count) {
            difference = auxhead2Count - auxhead1Count;
            result = advance(head1, difference);
            System.out.println(result.data);
            return result.data;
        }

        if (auxhead1Count == auxhead2Count) {
            LinkedList list = new LinkedList();

            while (head1.next != null) {
                list.add(head1);
                head1 = head1.next;
            }

            while (head2.next != null) {
                if (list.contains(head2)) {
                    result = head2;
                    break;
                }
                head2 = head2.next;
            }

//            difference = auxhead2Count - auxhead1Count;
//            result = advance(head1, difference);
        }

        System.out.println(result.data);
        return result.data;
    }

    private static SinglyLinkedListNode advance(SinglyLinkedListNode head, int steps) {
        for (int i = 0; i < steps; i++) {
            head = head.next;
        }
        return head;
    }

    static class Demo {
        private static SinglyLinkedListNode head1;
        private static SinglyLinkedListNode head2;

        public static void main(String[] args) {
            generateList2();
            Solution.findMergeNode(head1, head2);
        }

        private static void generateList1() {
            SinglyLinkedListNode node1 = new SinglyLinkedListNode(1);
            SinglyLinkedListNode node2 = new SinglyLinkedListNode(2);
            SinglyLinkedListNode node3 = new SinglyLinkedListNode(3);
            SinglyLinkedListNode node4 = new SinglyLinkedListNode(4);
            SinglyLinkedListNode node5 = new SinglyLinkedListNode(1);

            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = null;
            node5.next = node3;
            head1 = node1;
            head2 = node5;
        }

        private static void generateList2() {
            SinglyLinkedListNode node1L1 = new SinglyLinkedListNode(1);
            SinglyLinkedListNode node2L1 = new SinglyLinkedListNode(3);

            SinglyLinkedListNode node1L2 = new SinglyLinkedListNode(2);
            SinglyLinkedListNode node2L2 = new SinglyLinkedListNode(4);


            SinglyLinkedListNode node1ML = new SinglyLinkedListNode(5);
            SinglyLinkedListNode node2ML = new SinglyLinkedListNode(6);
            SinglyLinkedListNode node3ML = new SinglyLinkedListNode(7);

            node1L1.next = node2L1;
            node2L1.next = node1ML;
            // node1L1.next = node1ML;


            node1L2.next = node2L2;
            node2L2.next = node1ML;

            node1ML.next = node2ML;
            node2ML.next = node3ML;
            node3ML.next = null;

            head1 = node1L1;
            head2 = node1L2;


        }

    }

}
