package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

/**
 * @author AShuo
 * @date 2021年10月13日 15:17
 * 
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 为了让您更好地理解问题，以下面的二叉搜索树为例：
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer36 {
    public static void main(String[] args) {
//                 4
//                / \
//               2   5
//              / \   \
//             1   3   6
//To
//               1<==>2<==>3<==>4<==>5<==>6
//                \\                     //
//                    ==>            <==
        TreeNode treeNodeA1 = new TreeNode(4);
        TreeNode treeNodeA2 = new TreeNode(2);
        TreeNode treeNodeA3 = new TreeNode(5);
        TreeNode treeNodeA4 = new TreeNode(1);
        TreeNode treeNodeA5 = new TreeNode(3);
        TreeNode treeNodeA6 = new TreeNode(6);
        treeNodeA1.left = treeNodeA2;
        treeNodeA1.right = treeNodeA3;
        treeNodeA2.left = treeNodeA4;
        treeNodeA2.right = treeNodeA5;
        treeNodeA3.right = treeNodeA6;

        //midDfs(treeNodeA1);
        new Solution().treeToDoublyList(treeNodeA1);
    }

    //因为二叉搜索树且中序遍历，所以在遍历的过程中节点数据是有序的，只要让cur.left指向前一个节点pre,pre.right指向cur,之后pre向前，继续遍历
    //遍历完成二叉树最后一个节点时，pre指向该节点，head一直指向头节点，让head.left = pre，pre.right = head进行头尾关联
    //https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/solution/mian-shi-ti-36-er-cha-sou-suo-shu-yu-shuang-xian-5/
    static class Solution {
        TreeNode head, pre;

        public TreeNode treeToDoublyList(TreeNode root) {
            if (root == null) {
                return null;
            }
            dfs(root);
            head.left = pre;
            pre.right = head;
            return head;
        }

        public void dfs(TreeNode cur) {
            if (cur == null) {
                return;
            }
            dfs(cur.left);
            //加这个判断是因为，如果是第一次进行变换的节点，因为二叉搜索树且中序遍历，所以代表当前节点是最小的，让head指向当前节点，只会执行一次
            if (pre == null) {
                head = cur;
            } else {
                pre.right = cur;
            }
            cur.left = pre;
            pre = cur;
            dfs(cur.right);
        }


        //中序遍历
        public void midDfs(TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            midDfs(treeNode.left);
            System.out.println(treeNode.val);
            midDfs(treeNode.right);
        }
    }
}
