package t22_SubSequenceOfStack;

import com.base.Solution;

import java.util.Stack;

public class SubSequenceOfStackSolution {

    @Solution
    public boolean judge(int[] input, int[] subsequence) {
        if ((input == null || input.length == 0) && (subsequence == null || subsequence.length == 0)) {
            return true;
        }
        if (input == null || input.length == 0) {
            return false;
        }
        if (subsequence == null || subsequence.length == 0) {
            return false;
        }
        if (input.length != subsequence.length) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int mark = 0;
        for (int i : input) {
            stack.push(i);
            while (mark < subsequence.length && !stack.isEmpty() && stack.peek() == subsequence[mark]) {
                stack.pop();
                mark++;
            }
        }
        return stack.isEmpty();
    }
}
