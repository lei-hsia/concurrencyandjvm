package com.lei.javase.interviews.leetcode.bytedance;

import java.util.Stack;

public class MinStack155 {
    int minVal = Integer.MAX_VALUE;
    Stack<Integer> st = new Stack();

    /** initialize your data structure here. */
    public MinStack155() {

    }

    public void push(int x) {
        if (x <= minVal) { // 和minVal相等时，相同的处理
            st.push(minVal);
            minVal = x;
        }
        st.push(x);
    }

    public void pop() {
        if (st.pop() == minVal) minVal = st.pop(); // 注: pop了两次
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return minVal;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */