package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AShuo
 * @date 2021年09月24日 14:06
 * 
 * 剑指 Offer 35. 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * 
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 
 * 解释：给定的链表为空（空指针），因此返回 null。
 * 
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer35 {
    public static void main(String[] args) {
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        //(单链表复制)
        System.out.println("单链表复制前：" + node1);
        System.out.println("单链表复制后：" + new Solution().copyListNode(node1));

        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;
        //复杂链表复制
        System.out.println("复杂链表复制前：" + node1);
        System.out.println("复杂链表复制后：" + new Solution().copyRandomList(node1));
    }

    //构建"原链表节点"和"新链表对应节点的键值"对映射关系，再遍历构建新链表各节点的 next 和 random 引用指向
    //https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/solution/jian-zhi-offer-35-fu-za-lian-biao-de-fu-zhi-ha-xi-/
    static class Solution {
        //单链表复制
        public Node copyListNode(Node head) {
            if (head == null) {
                return head;
            }
            //该节点是头节点，目的是返回复制的链表dum.next
            Node dum = new Node();
            Node pre = dum;
            Node cur = head;
            while (cur != null) {
                //复制节点 cur
                Node node = new Node(cur.val);
                //新链表的 前驱节点 -> 当前节点
                pre.next = node;
                //新链表的 前驱节点 -> 当前节点
                cur = cur.next;
                // 保存当前新节点
                pre = node;
            }
            return dum.next;
        }

        public Node copyRandomList(Node head) {
            if (head == null) {
                return head;
            }
            Map<Node, Node> map = new HashMap<>();
            Node cur = head;
            while (cur != null) {
                map.put(cur, new Node(cur.val));
                cur = cur.next;
            }
            cur = head;
            while (cur != null) {
                map.get(cur).next = map.get(cur.next);
                map.get(cur).random = map.get(cur.random);
                cur = cur.next;
            }
            return map.get(head);
        }
    }
}
