package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author AShuo
 * @date 2021年10月18日 10:48
 *
 * 剑指 Offer 55 - I. 二叉树的深度
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *      / \
 *      5  7
 * 返回它的最大深度 3 。
 *
 * 提示：
 * 节点总数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer55 {
    public static void main(String[] args) {
        TreeNode treeNodeA1 = new TreeNode(3);
        TreeNode treeNodeA2 = new TreeNode(9);
        TreeNode treeNodeA3 = new TreeNode(20);
        TreeNode treeNodeA4 = new TreeNode(15);
        TreeNode treeNodeA5 = new TreeNode(7);
        treeNodeA1.left = treeNodeA2;
        treeNodeA1.right = treeNodeA3;
        treeNodeA2.left = treeNodeA4;
        treeNodeA3.right = treeNodeA5;

        System.out.println(new Solution().maxDepth(treeNodeA1));
        System.out.println(new Solution().isBalanced(treeNodeA1));
    }

    static class Solution {
        public int maxDepth(TreeNode root) {
            //递归解法
            //后序遍历递归获取节点最大值
            //return root==null? 0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;

            //迭代解法(这里使用)
            //当节点不为空时，将左右节点同时入栈，count加一，再将节点全部出栈，当其出栈的左右节点又不为空时，再进行入栈，直到左右节点为空，即全部都到达了根节点
            //https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/solution/mian-shi-ti-55-i-er-cha-shu-de-shen-du-xian-xu-bia/
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            int count = 0;
            queue.add(root);
            while (!queue.isEmpty()) {
                count++;
                for (int i = queue.size(); i > 0; i--) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return count;
        }

        /**
         * @return
         * @author AShuo
         * @date 2021/10/18 11:41
         *
         * 剑指 Offer 55 - II. 平衡二叉树
         * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
         *
         * 示例 1:
         * 给定二叉树 [3,9,20,null,null,15,7]
         *    3
         *   / \
         *  9  20
         *     / \
         *     15 7
         * 返回 true 。
         *
         * 示例 2:
         * 给定二叉树 [1,2,2,3,3,null,null,4,4]
         *
         *     1
         *    / \
         *   2   2
         *  / \
         * 3   3
         *    / \
         *    4   4
         * 返回 false 。
         *
         * 限制：
         * 0 <= 树的结点个数 <= 10000
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        public boolean isBalanced(TreeNode root) {
/*      //前一问的递归求树最大深度另一个方法
        if (root == null) return 0;
        int left = isBalanced(root.left);
        int right =isBalanced(root.right);
        return Math.max(left,right)+1;*/
            return recur(root) != -1;
        }

        //后序遍历树节点，当遍历完一个节点的左右节点后，判断左右节点的长度差值，差值大于1时，表示不是二叉平衡树，返回-1
        //当递归遍历判断长度返回-1时，直接返回，后面的遍历不再进行，节约时间，俗称"剪枝"
        //https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/solution/mian-shi-ti-55-ii-ping-heng-er-cha-shu-cong-di-zhi/
        public int recur(TreeNode root) {
            if (root == null) return 0;
            int left = recur(root.left);
            if (left == -1) {
                return -1;
            }
            int right = recur(root.right);
            if (right == -1) {
                return -1;
            }
            return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
        }
    }
}
