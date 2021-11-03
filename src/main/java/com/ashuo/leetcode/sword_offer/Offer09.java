package com.ashuo.leetcode.sword_offer;

import java.util.Stack;

/**
 * @author AShuo
 * @date 2021年09月07日 9:43
 * 
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 * 
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer09 {
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }

    //对于栈A，创建一个辅助栈B，当入栈时，将元素入A栈，当出栈时，因为要返回队列首（栈底）元素，所以可以先将A栈的元素全部弹出放入B栈，完成后B栈的栈顶元素既是A栈的栈底元素，返回即可
    //https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/solution/mian-shi-ti-09-yong-liang-ge-zhan-shi-xian-dui-l-2/
    public static class CQueue {
        private final Stack<Integer> A;
        private final Stack<Integer> B;

        public CQueue() {
            A = new Stack<>();
            B = new Stack<>();
        }

        public void appendTail(int value) {
            //push时使用A保存数据
            A.push(value);
        }

        public int deleteHead() {
            //当B栈中还有数据时，可以直接返回B栈的栈顶元素（因为栈顶元素是A栈的栈底元素）
            if (!B.isEmpty()) {
                return B.pop();
            } else if (A.isEmpty() && B.isEmpty()) {
                //如果A、B栈都为空，返回-1
                return -1;
            } else {
                //将A栈的所有元素逆序存放的B栈中
                while (!A.isEmpty()) {
                    B.push(A.pop());
                }
                return B.pop();
            }
        }
    }
}
