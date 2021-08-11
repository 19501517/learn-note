package t2_AddTwoNumbers;


import commonmodel.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbersTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(ListNode.valueOfArray(2, 4, 3), ListNode.valueOfArray(5, 6, 4), ListNode.valueOfArray(7, 0, 8)),
                Arguments.of(ListNode.valueOfArray(0, 1), ListNode.valueOfArray(0, 1, 2), ListNode.valueOfArray(0, 2, 2)),
                Arguments.of(ListNode.valueOfArray(5), ListNode.valueOfArray(5), ListNode.valueOfArray(0, 1)));
    }

    @ParameterizedTest
    @MethodSource("getParams")
    @DisplayName("exhaustion solution")
    public void solutionTest(ListNode<Integer> l1, ListNode<Integer> l2, ListNode<Integer> result) {
        IAddTwoNumbersSolution solution = new AddTwoNumbersSolution();
        assertAndPrint(l1, l2, result, solution);
    }

    private void assertAndPrint(ListNode<Integer> l1, ListNode<Integer> l2, ListNode<Integer> expectedResult, IAddTwoNumbersSolution solution) {
        long start = System.currentTimeMillis();
        ListNode<Integer> actualResult = solution.addTwoNumbers(l1, l2);
        long end = System.currentTimeMillis();
        System.out.println("----------------------------------------");
        System.out.println("given ListNode 1 : " + printListNode(l1));
        System.out.println("given ListNode 2 : " + printListNode(l2));
        System.out.println("actual result : " + printListNode(actualResult));
        System.out.println("expect result : " + printListNode(expectedResult));
        System.out.println("time : " + (end - start));

        while (expectedResult != null && actualResult != null) {
            if (expectedResult.val.equals(actualResult.val)) {
                Assertions.fail();
            }
            expectedResult = expectedResult.next;
            actualResult = actualResult.next;
        }
        if (expectedResult != null || actualResult != null) {
            Assertions.fail();
        }
    }

    private String printListNode(ListNode<?> node) {
        if (node == null) {
            return "null";
        }

        ListNode<?> p = node;

        StringBuilder sb = new StringBuilder();
        sb.append(p.val);
        while (p.next != null) {
            p = p.next;
            sb.append(" -> ").append(p.val);
        }
        return sb.toString();
    }
}