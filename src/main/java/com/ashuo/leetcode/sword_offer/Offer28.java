package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

/**
 * @author AShuo
 * @date 2021年09月27日 11:23
 *
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *      1
 *     / \
 *    2   2
 *   / \ / \
 *   3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *      1
 *     / \
 *    2   2
 *     \   \
 *      3    3
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 限制：
 * 0 <= 节点个数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer28 {
    public static void main(String[] args) {
        TreeNode treeNodeA1 = new TreeNode(1);
        TreeNode treeNodeA2 = new TreeNode(2);
        TreeNode treeNodeA3 = new TreeNode(2);
        TreeNode treeNodeA4 = new TreeNode(3);
        TreeNode treeNodeA5 = new TreeNode(3);
        TreeNode treeNodeA6 = new TreeNode(4);
        TreeNode treeNodeA7 = new TreeNode(4);
        treeNodeA1.left = treeNodeA2;
        treeNodeA1.right = treeNodeA3;
        treeNodeA2.left = treeNodeA4;
        treeNodeA2.right = treeNodeA6;
        treeNodeA3.left = treeNodeA7;
        treeNodeA3.right = treeNodeA5;
        System.out.println(new Solution().isSymmetric(treeNodeA1));
    }

    static class Solution {
        public boolean isSymmetric(TreeNode root) {
            return root == null || recur(root.left, root.right);
        }

        //递归遍历左节点的左节点和右节点的右节点，如果两个节点同时为空，则表示此树从顶至底的节点都对称，如果有一个节点不为空或者节点的值不相同，则表示不对称
        //https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/solution/mian-shi-ti-28-dui-cheng-de-er-cha-shu-di-gui-qing/
        private boolean recur(TreeNode L, TreeNode R) {
            if (L == null && R == null) {
                return true;
            }
            if (L == null || R == null || L.val != R.val) {
                return false;
            }
            return recur(L.left, R.right) && recur(L.right, R.left);
        }
    }
}
