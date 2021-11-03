package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

/**
 * @author AShuo
 * @date 2021年09月27日 9:35
 *
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *     3
 *    / \
 *   4   5
 *  / \
 * 1   2
 *
 * 给定的树 B：
 *   4
 *  /
 * 1
 *
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 * 限制：
 * 0 <= 节点个数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer26 {
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

//               4
//              /
//             1
//              \
//               8
        TreeNode treeNodeB1 = new TreeNode(4);
        TreeNode treeNodeB2 = new TreeNode(1);
        TreeNode treeNodeB3 = new TreeNode(8);
        treeNodeB1.left = treeNodeB2;
        treeNodeB2.right = treeNodeB3;
        System.out.println(new Solution().isSubStructure(treeNodeA1, treeNodeB1));
    }

    //先序遍历每个节点，在遍历的同时递归遍历判断每个节点的左节点和右节点是否和另一个数的左右节点值相同
    //https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
    static class Solution {
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            //先序遍历
            return (A != null && B != null) && ((recur(A, B)) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
        }

        public boolean recur(TreeNode A, TreeNode B) {
            //第二个树没有左或右子节点，返回true
            if (B == null) {
                return true;
            }
            //第一个树没有左或右子节点，第一个树的当前节点表示遍历已经到底了，此时第二个树因为不是同构而较深，即此时B树比A树较深
            if (A == null) {
                return false;
            }
            //判断对应左右节点值是否相同
            return (A.val == B.val && recur(A.left, B.left) && recur(A.right, B.right));
        }
    }
}
