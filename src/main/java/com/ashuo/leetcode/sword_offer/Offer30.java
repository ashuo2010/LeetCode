package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月03日 17:53
 * 
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * 
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 * 
 * 提示：
 * 各函数的调用总次数不超过 20000 次
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class Offer30 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.min());
    }

    //对于栈A，创建一个辅助栈B用于储存A栈中的最小值
    //当元素入A栈时，如果B栈为空或者B栈栈顶元素小于添加的元素,则将元素也入B栈
    //获取A栈中最小的元素即返回B栈栈顶元素即可
    //当A出栈时，如果出栈的元素与B栈栈顶相同时，B栈的栈顶元素也要出栈，保证B栈的栈顶元素一定是A栈存在且最小的
    //https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/submissions/
    static class MinStack {

        Stack<Integer> A, B;

        public MinStack() {
            A = new Stack<>();
            B = new Stack<>();
        }

        public void push(int x) {
            A.push(x);
            if (B.isEmpty() || B.peek() >= x) {
                B.push(x);
            }
        }

        public void pop() {
            if (A.pop().equals(B.peek())) {
                B.pop();
            }
        }

        public int top() {
            return A.peek();
        }

        public int min() {
            return B.peek();
        }
    }

}
