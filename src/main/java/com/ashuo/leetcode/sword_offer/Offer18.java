package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.ListNode;

/**
 * @author AShuo
 * @date 2021年10月11日 9:14
 * 
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 * 注意：此题对比原题有改动
 * 
 * 示例 1:
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 
 * 示例 2:
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * 
 * 说明：
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer18 {
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
        System.out.println(new Solution().deleteNode(listNode1, 3));
    }

    //双指针，用pre代表链表的前一个结点，cur代表当前的节点，当当前节点的值与指定节点的值相同时，让前一个节点的next指向当前的next指向的节点
    //这样相当于删除了cur的节点，特例：当头节点就是要查找的元素时，因为cur和pre都指向当前头节点，所以pre.next = cur.next，相当于cur.next=cur.next,无作用效果
    //所以当头节点就是要找的节点时，直接返回头节点的next
    //https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/solution/mian-shi-ti-18-shan-chu-lian-biao-de-jie-dian-sh-2/
    static class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            if (head.val == val) {
                return head.next;
            }
            ListNode pre = head;
            ListNode cur = head;
            while (cur != null && cur.val != val) {
                pre = cur;
                cur = cur.next;
            }
            if (cur != null) {
                pre.next = cur.next;
            }
            return head;
        }
    }
}
