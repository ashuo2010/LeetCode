package com.ashuo.leetcode.sword_offer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author AShuo
 * @date 2021年10月15日 15:47
 * 
 * 剑指 Offer 41. 数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 
 * 示例 1：
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 
 * 示例 2：
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 * 
 * 限制：
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer41 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    //利用最小堆A和最大堆B存放数组的数据，当已添加到A、B中的元素为奇数时，
    //下一个元素添加到B中元素加一(即先添加到A中，在弹出最大的元素添加到B中，最后A的长度不变，B的长度加一)，
    //同理，偶数时A中元素加一
    //任意时刻A的堆顶元素小于等于B的对顶元素
    //https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/solution/mian-shi-ti-41-shu-ju-liu-zhong-de-zhong-wei-shu-y/
    static class MedianFinder {
        Queue<Integer> A, B;

        public MedianFinder() {
            A = new PriorityQueue<>();
            B = new PriorityQueue<>((x, y) -> (y - x));
        }

        public void addNum(int num) {
            if (A.size() == B.size()) {
                B.add(num);
                A.add(B.poll());
            } else {
                A.add(num);
                B.add(A.poll());
            }
        }

        public double findMedian() {
            return A.size() == B.size() ? (A.peek() + B.peek()) / 2.0 : A.peek();
        }
    }
}

