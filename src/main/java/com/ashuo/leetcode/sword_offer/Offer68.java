package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

/**
 * @author AShuo
 * @date 2021年10月19日 14:18
 * 
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * 
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer68 {
    public static void main(String[] args) {
        TreeNode treeNodeA1 = new TreeNode(6);
        TreeNode treeNodeA2 = new TreeNode(2);
        TreeNode treeNodeA3 = new TreeNode(8);
        TreeNode treeNodeA4 = new TreeNode(0);
        TreeNode treeNodeA5 = new TreeNode(4);
        TreeNode treeNodeA6 = new TreeNode(7);
        TreeNode treeNodeA7 = new TreeNode(9);
        TreeNode treeNodeA8 = new TreeNode(3);
        TreeNode treeNodeA9 = new TreeNode(5);

//                2
//              /    \
//            6       8
//          /  \      / \
//        0     4    7    9
//            /   \
//           3     5
        treeNodeA1.left = treeNodeA2;
        treeNodeA1.right = treeNodeA3;
        treeNodeA2.left = treeNodeA4;
        treeNodeA2.right = treeNodeA5;
        treeNodeA3.left = treeNodeA6;
        treeNodeA3.right = treeNodeA7;
        treeNodeA5.left = treeNodeA8;
        treeNodeA5.right = treeNodeA9;
        System.out.println(new Solution().lowestCommonAncestor(treeNodeA1, new TreeNode(2), new TreeNode(4)));


        TreeNode treeNodeB1 = new TreeNode(3);
        TreeNode treeNodeB2 = new TreeNode(5);
        TreeNode treeNodeB3 = new TreeNode(1);
        TreeNode treeNodeB4 = new TreeNode(6);
        TreeNode treeNodeB5 = new TreeNode(2);
        TreeNode treeNodeB6 = new TreeNode(0);
        TreeNode treeNodeB7 = new TreeNode(8);
        TreeNode treeNodeB8 = new TreeNode(7);
        TreeNode treeNodeB9 = new TreeNode(4);
//                3
//              /    \
//            5       1
//          /  \      / \
//        6     2    0    8
//            /   \
//           7     4
        treeNodeB1.left = treeNodeB2;
        treeNodeB1.right = treeNodeB3;
        treeNodeB2.left = treeNodeB4;
        treeNodeB2.right = treeNodeB5;
        treeNodeB3.left = treeNodeB6;
        treeNodeB3.right = treeNodeB7;
        treeNodeB5.left = treeNodeB8;
        treeNodeB5.right = treeNodeB9;
        System.out.println(new Solution().lowestCommonAncestor2(treeNodeB1, new TreeNode(7), new TreeNode(4)));
    }

    static class Solution {
        //因为是二叉搜索树且树节点的值都不相同，所以从根节点出发，当p节点和q节点都大于当前节点时，表明p、q的最近的共同节点在右边
        //同理当p，q都小于当前节点时，最近的共同节点在左边，当p、q一个大于等于一个小于等于时，此节点即最近共同父节点
        //https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-i-er-cha-sou-suo-shu-de-zui-jin-g-7/
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       /* while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                //都大于时，共同节点在右边
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                //都小于时，共同节点在左边
                root = root.left;
            } else {
                break;
            }
        }
        return root;
    }*/
            //递归
            if (p.val > root.val && q.val > root.val) {
                return lowestCommonAncestor(root.right, p, q);
            }
            if (p.val < root.val && q.val < root.val) {
                return lowestCommonAncestor(root.left, p, q);
            }
            return root;
        }

        /**
         * @author AShuo
         * @date 2021/10/19 16:05
         * 
         * 剑指 Offer 68 - II. 二叉树的最近公共祖先
         * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
         * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
         * 
         * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
         * 
         * 示例 1:
         * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
         * 输出: 3
         * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
         * 
         * 示例 2:
         * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
         * 输出: 5
         * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
         * 
         * 说明:
         * 所有节点的值都是唯一的。
         * p、q 为不同节点且均存在于给定的二叉树中。
         * 
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        //先序遍历，找到对应节点p和节点q，如果在左边找到了p或q，就返回左边的，同理右边也是如此，如果同时找到了就返回根节点
        //https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
        public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor2(root.left, p, q);
            TreeNode right = lowestCommonAncestor2(root.right, p, q);
            if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                return root;
            }
        }
    }
}
