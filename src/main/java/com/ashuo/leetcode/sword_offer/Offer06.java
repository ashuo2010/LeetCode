package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.ListNode;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年09月06日 14:41
 * 
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * 
 * 限制：
 * 0 <= 链表长度 <= 10000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer06 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        Arrays.stream(new Solution().reversePrint(listNode1)).forEach(System.out::println);
    }

    //计算链表的长度，用于初始化数组的大小，让数组可以从尾部开始插入元素，之后遍历链表，从数组后面往前添加元素，再返回数组
    //https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/solution/mian-shi-ti-06-cong-wei-dao-tou-da-yin-lian-biao-d/

    static class Solution {
        public int[] reversePrint(ListNode head) {
            ListNode node = head;
            int length = 0;
            //计算链表的长度，用于初始化数组的大小，让数组可以从尾部开始插入元素
            while (node != null) {
                node = node.next;
                length++;
            }
            int[] reverseArray = new int[length];
            //从链表尾部开始插入元素
            for (int i = length - 1; i >= 0; i--) {
                reverseArray[i] = head.val;
                head = head.next;
            }
            return reverseArray;
        }
    }
}
