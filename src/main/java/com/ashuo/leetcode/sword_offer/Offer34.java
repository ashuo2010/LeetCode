package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author AShuo
 * @date 2021年10月13日 11:41
 * 
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * 
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * 
 * 提示：
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer34 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(11);
        TreeNode treeNode5 = new TreeNode(13);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(2);
        TreeNode treeNode9 = new TreeNode(5);
        TreeNode treeNode10 = new TreeNode(1);
//                    5
//                  /    \
//                4        8
//              /         /  \
//            11        13    4
//           /  \            / \
//          7    2           5  1
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;
        treeNode4.left = treeNode7;
        treeNode4.right = treeNode8;
        treeNode6.left = treeNode9;
        treeNode6.right = treeNode10;
        System.out.println(new Solution().pathSum(treeNode1, 22));
    }

    //用两个list保存当前遍历的路径和符合条件的路径，当前节点是叶子节点且路径总值和target相等的路径即是符合条件的路径
    //https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
    static class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int target) {
            //用于保存当前遍历的路径，[x]
            LinkedList<Integer> path = new LinkedList<>();
            //用于保存当前已经遍历符合条件的路径[[xxx],[xxx]]
            List<List<Integer>> res = new ArrayList<>();
            recur(root, target, path, res);
            return res;
        }

        public void recur(TreeNode root, int tar, LinkedList<Integer> path, List<List<Integer>> res) {
            //如果到了叶子节点下一个节点
            if (root == null) {
                return;
            }
            //保存路径
            path.add(root.val);
            //计算当前路径还差多少
            tar -= root.val;
            //当到路径计算的值和target相等且当前节点是叶子节点时，保存这个符合条件的路径
            if (tar == 0 && root.left == null && root.right == null) {
                //传递的path是对象的地址值，不是一个新的对象，对path操作是在原来的对象操作，当回溯到根节点时，removeLast导致path=[]，所以需要创建一个新的对象
                res.add(new LinkedList<>(path));
            }
            //遍历左右节点
            recur(root.left, tar, path, res);
            recur(root.right, tar, path, res);
            //当遍历回溯时，删除已遍历的当前路径的一部分
            path.removeLast();
        }
    }
}
