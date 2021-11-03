package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

/**
 * @author AShuo
 * @date 2021年09月27日 10:52
 *
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *     4
 *    /  \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 镜像输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 限制：
 * 0 <= 节点个数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer27 {
    public static void main(String[] args) {
//                 3
//                / \
//               4   5
//              / \   \
//             1   2   6
//            / \   \
//          7    8   9
        TreeNode treeNodeA1 = new TreeNode(3);
        TreeNode treeNodeA2 = new TreeNode(4);
        TreeNode treeNodeA3 = new TreeNode(5);
        TreeNode treeNodeA4 = new TreeNode(1);
        TreeNode treeNodeA5 = new TreeNode(2);
        TreeNode treeNodeA6 = new TreeNode(6);
        TreeNode treeNodeA7 = new TreeNode(7);
        TreeNode treeNodeA8 = new TreeNode(8);
        TreeNode treeNodeA9 = new TreeNode(9);
        treeNodeA1.left = treeNodeA2;
        treeNodeA1.right = treeNodeA3;
        treeNodeA2.left = treeNodeA4;
        treeNodeA2.right = treeNodeA5;
        treeNodeA3.left = treeNodeA6;
        treeNodeA4.left = treeNodeA7;
        treeNodeA4.right = treeNodeA8;
        treeNodeA5.right = treeNodeA9;

        System.out.println(new Solution().mirrorTree(treeNodeA1));
    }

    //递归遍历交换左右节点
    //https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/solution/mian-shi-ti-27-er-cha-shu-de-jing-xiang-di-gui-fu-/
    static class Solution {
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            //保存当前右节点的值，因为当执行root.right=mirrorTree(root.left)后，root.right的值已经改变
            TreeNode tmp = root.right;
            root.right = mirrorTree(root.left);
            root.left = mirrorTree(tmp);
            return root;
        }
    }
}
