package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author AShuo
 * @date 2021年10月27日 9:37
 * 
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 
 * 解释:
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer59 {

    public static void main(String[] args) {
        Arrays.stream(new Solution().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)).forEach(System.out::println);
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
    }

    static class Solution {
        //创建一个双端队列，队列的首元素为滑动窗口遍历的窗口内元素的最大值，每一次滑动后获取队列的首元素添加到数组中，最后返回数组即可
        //难点在于怎么保证双端队列内的元素递减的
        //将要添加的元素尾元素有三种情况
        //添加的元素<=尾元素     直接加到队列尾部
        //添加的元素>尾元素      循环删除比添加的元素小的尾元素，再进行添加，确保队列是递减有序的
        //再遍历的过程中，当窗口移动，导致上一个窗口的内的第一个元素移除初窗口之外时，需要将队列的首个元素移除，因为队列的首个元素此时已经不在滑动窗口内了
        //https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/solution/mian-shi-ti-59-i-hua-dong-chuang-kou-de-zui-da-1-6/
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 0) {
                return nums;
            }
            //窗口右边界从0开始出发，当未形成窗口时
            int[] res = new int[nums.length - k + 1];
            Deque<Integer> queue = new LinkedList<>();
            for (int i = 0; i < k; i++) {
                while (!queue.isEmpty() && nums[i] > queue.getLast()) {
                    queue.removeLast();
                }
                queue.addLast(nums[i]);
            }

            res[0] = queue.getFirst();
            //当已经形成窗口时
            for (int i = k; i < nums.length; i++) {
                //当窗口移动后，队列的首个元素已经不在窗口内了，移除首个元素
                if (nums[i - k] == queue.getFirst()) {
                    queue.removeFirst();
                }
                while (!queue.isEmpty() && nums[i] > queue.getLast()) {
                    queue.removeLast();
                }
                queue.addLast(nums[i]);

                res[i - k + 1] = queue.getFirst();
            }
            return res;
        }
    }


    /**
     * @author AShuo
     * @date 2021/10/27 11:14
     * 
     * 剑指 Offer 59 - II. 队列的最大值
     * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
     * 若队列为空，pop_front 和 max_value 需要返回 -1
     * 
     * 示例 1：
     * 输入:
     * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
     * [[],[1],[2],[],[],[]]
     * 输出: [null,null,null,2,1,2]
     * 
     * 示例 2：
     * 输入:
     * ["MaxQueue","pop_front","max_value"]
     * [[],[],[]]
     * 输出: [null,-1,-1]
     * 
     * 限制：
     * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
     * 1 <= value <= 10^5
     * 
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //额外创建一个双向队列，用于维护最大值的队列
    //每次向队列增加一个元素是，额外向另一个双向队列也添加元素，双向队列中的元素需要有序递减
    //为了确保队列中的元素是递减的：
    //当添加的元素<=尾元素     直接加到队列尾部
    //当添加的元素>尾元素      循环删除比添加的元素小的尾元素，再进行添加，确保队列是递减有序的
    //当调用获取元素最大值时，弹出返回双向队列的首个元素即可，因为该元素是当前普通队列的最大值
    //当弹出普通队列的元素和双向队列的首元素相等时，双向队列的首元素也应该出队列
    //采用双向队列的原因是队列需要进行尾删，且题目时间复杂度需要O(1),故不能采用普通队列
    //https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/solution/jian-zhi-offer-59-ii-dui-lie-de-zui-da-z-0pap/
    static class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> deque;

        public MaxQueue() {
            this.queue = new LinkedList<>();
            this.deque = new LinkedList<>();
        }

        public int max_value() {
            return this.deque.isEmpty() ? -1 : deque.peekFirst();
        }

        public void push_back(int value) {
            queue.offer(value);
            while (!deque.isEmpty() && value > deque.peekLast()) {
                deque.pollLast();
            }
            deque.offerLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            if (queue.peek().equals(deque.peekFirst())) {
                deque.pollFirst();
            }
            return queue.poll();
        }
    }
}
