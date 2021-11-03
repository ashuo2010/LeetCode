package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.ListNode;

/**
 * @author AShuo
 * @date 2021年10月11日 9:54
 * 
 * 剑指 Offer 25. 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 
 * 示例1：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 
 * 限制：
 * 0 <= 链表长度 <= 1000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer25 {
    public static void main(String[] args) {
        ListNode listNodeA1 = new ListNode(1);
        ListNode listNodeA2 = new ListNode(2);
        ListNode listNodeA3 = new ListNode(3);
        listNodeA1.next = listNodeA2;
        listNodeA2.next = listNodeA3;

        ListNode listNodeB1 = new ListNode(1);
        ListNode listNodeB2 = new ListNode(3);
        ListNode listNodeB3 = new ListNode(4);
        listNodeB1.next = listNodeB2;
        listNodeB2.next = listNodeB3;

        System.out.println(new Solution().mergeTwoLists(listNodeA1, listNodeB1));
    }

    //创建一个新链表，当l1.val<l2.val时，让l1添加到新链表中，否则让l2添加到新链表中，添加的过程中要移动链表的位置
    //当一个链表首先添加完之后，另一个链表一定还有没有添加完的元素，且首先添加完成的链表的最后一个元素一定小于另一个链表所有还没有添加完的元素，
    //所以可以直接将这些没有添加完的元素加到新链表后面
    //https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/solution/mian-shi-ti-25-he-bing-liang-ge-pai-xu-de-lian-b-2/
    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dum = new ListNode(0);
            ListNode cur = dum;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            cur.next = l1 != null ? l1 : l2;
            return dum.next;
        }
    }
}
