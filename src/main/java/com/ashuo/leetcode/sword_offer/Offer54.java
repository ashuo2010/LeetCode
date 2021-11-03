package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

/**
 * @author AShuo
 * @date 2021年10月13日 16:24
 *
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *      3
 *     / \
 *    1   4
 *     \
 *      2
 * 输出: 4
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *      5
 *     / \
 *    3   6
 *   / \
 *  2   4
 * /
 * 1
 * 输出: 4
 *
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer54 {
    public static void main(String[] args) {

//                 5
//                / \
//               3   6
//              / \
//             2   4
//            /
//           1
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(1);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode4.left = treeNode6;

        System.out.println(new Solution().kthLargest(treeNode1, 3));
    }

    //逆序中序遍历二叉搜索树，遍历的数据将从大到小，在遍历时计数，当到达指定位置时，返回结果
    //https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/solution/mian-shi-ti-54-er-cha-sou-suo-shu-de-di-k-da-jie-d/
    static class Solution {
        int res = 0, count = 0;

        public int kthLargest(TreeNode root, int k) {
            count = k;
            dfs(root);
            return res;
        }

        public void dfs(TreeNode root) {
            if (root == null) {
                return;
            }
            dfs(root.right);
            //中序遍历过程中，判断当前的是否已满足倒数第k个了
            if (count == 0) {
                return;
            }
            if (--count == 0) {
                res = root.val;
                return;
            }
            dfs(root.left);
        }

/*   暴力破解
    public int kthLargest(TreeNode root, int k) {
        ArrayList<Integer> list=new ArrayList();
        dfs(root,list);
        return list.get(k-1);
    }

    public void dfs(TreeNode root,ArrayList list){
        if (root == null) {
            return;
        }
        dfs(root.right,list);
        list.add(root.val);
        dfs(root.left,list);
    }*/

    }
}

