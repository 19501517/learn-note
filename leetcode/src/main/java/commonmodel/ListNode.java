package commonmodel;

public class ListNode<T> {
    public T val;
    public ListNode<T> next;

    public static <T> ListNode<T> valueOfArray(T... ts) {
        if (ts == null) {
            return new ListNode<>(null);
        }

        ListNode<T> head = new ListNode<>(null);
        ListNode<T> tail = head;
        for (T t : ts) {
            tail.next = new ListNode<>(t);
            tail = tail.next;
        }
        return head.next;
    }

    public ListNode(T x) {
        val = x;
    }
}