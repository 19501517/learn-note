package utils;

import java.util.Stack;

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            int curMin =  minStack.peek();
            if (x < curMin) {
                minStack.push(x);
            }
        }
    }
    
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int out = stack.pop();
        int curMin = minStack.peek();
        if (out == curMin) {
            minStack.pop();
        }
    }
    
    public int top() {
        if (stack.isEmpty()) {
            return 0;
        }
        return stack.peek();
    }
    
    public int min() {
        if (minStack.isEmpty()) {
            return 0;
        }
        return minStack.peek();
    }
}