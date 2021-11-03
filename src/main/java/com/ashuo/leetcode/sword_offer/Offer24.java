package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.ListNode;

/**
 * @author AShuo
 * @date 2021年09月06日 14:41
 * 
 * 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * 
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 
 * 限制：
 * 0 <= 节点个数 <= 5000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer24 {
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

        System.out.println(new Solution().reversePrint(listNode1));
    }

    //创建三个指针，移动和保存链表指向的下一个节点
    //https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/jian-zhi-offer-24-fan-zhuan-lian-biao-die-dai-di-2/
    static class Solution {
        public ListNode reversePrint(ListNode head) {
            ListNode temp, pre = null, cur = head;
            while (cur != null) {
                //记录下一个节点，防止断链
                temp = cur.next;
                //后一个节点的指向改成指向前一个结点
                cur.next = pre;
                //暂存记录下调整后的链表
                pre = cur;
                //恢复记录下的下一个节点
                cur = temp;
            }
            return pre;
        }
    }
}
