package t2_AddTwoNumbers;

import com.test.module.ListNode;

/**
 * 自己的方法，leetcode上面的解决方法也是跟这个一样
 */
public class AddTwoNumbersSolution implements IAddTwoNumbersSolution {

    @Override
    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        int c = 0;
        ListNode<Integer> node1 = l1;
        ListNode<Integer> node2 = l2;
        ListNode<Integer> headNode = new ListNode<>(0);
        ListNode<Integer> tailNode = headNode;
        while (node1 != null || node2 != null) {
            int num1 = node1 == null ? 0 : node1.val;
            int num2 = node2 == null ? 0 : node2.val;

            int peace = num1 + num2 + c;

            c = peace / 10;
            ListNode<Integer> curNode = new ListNode<>(peace % 10);
            tailNode.next = curNode;
            tailNode = curNode;

            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }

        if (c == 1) {
            tailNode.next = new ListNode<>(1);
        }
        return headNode.next;
    }
}
