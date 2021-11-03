package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.ListNode;

/**
 * @author AShuo
 * @date 2021年10月11日 10:33
 * 
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * 输入两个链表，找出它们的第一个公共节点。
 * 
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * 
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer52 {
    public static void main(String[] args) {
//        1-2-3
//              \
//                4
//              /
//        2-3
        ListNode listNodeA1 = new ListNode(1);
        ListNode listNodeA2 = new ListNode(2);
        ListNode listNodeA3 = new ListNode(3);
        ListNode listNodeA4 = new ListNode(4);
        ListNode listNodeA5 = new ListNode(5);
        listNodeA1.next = listNodeA2;
        listNodeA2.next = listNodeA3;
        listNodeA3.next = listNodeA4;
        listNodeA4.next = listNodeA5;

// 2-3-4
        ListNode listNodeB1 = new ListNode(2);
        ListNode listNodeB2 = new ListNode(3);
        ListNode listNodeB3 = listNodeA4;
        listNodeB1.next = listNodeB2;
        listNodeB2.next = listNodeB3;

        System.out.println(new Solution().getIntersectionNode(listNodeA1, null));
    }

    //同时遍历两个链表，当指向的位置不相同时，2个链表同时移动，当移动到末端时，从另一个链表的右节点进行遍历，如果连个链表在遍历的过程中指向的节点相同
    //那么就有共同节点，退出循环，返回其中一个节点的值，当进行M+N(M,N分别表示两个链表的长度)次遍历后仍没有相同节点，那A和B都此时会指向null，退出循环，返回其中一个节点的值
    //1234 null  1 2 3 4 null 5 6 7 null
    //567 null   5 6 7 null 1 2 3 4 null
    //https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/jian-zhi-offer-52-liang-ge-lian-biao-de-gcruu/
    static class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode A = headA;
            ListNode B = headB;
            while (A != B) {
                A = A != null ? A.next : headB;
                B = B != null ? B.next : headA;
            }
            return A;
        }
    }
}
