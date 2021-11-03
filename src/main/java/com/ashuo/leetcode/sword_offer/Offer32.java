package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

import java.util.*;

/**
 * @author AShuo
 * @date 2021年09月26日 14:21
 *
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *    3
 *   / \
 *  9  20
 * /    \
 * 15    7
 *
 * 返回：
 * [3,9,20,15,7]
 *
 * 提示：
 * 节点总数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer32 {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode3.right = treeNode5;

        Arrays.stream(new Solution().levelOrder(treeNode1)).forEach(System.out::println);
        System.out.println(new Solution().levelOrder2(treeNode1));
        System.out.println(new Solution().levelOrder3(treeNode1));
        System.out.println(new Solution().levelOrder4(treeNode1));

    }


    //利用队列先进先出的特性，先把根节点放入队列中，再把节点出队列，把值放入列表中，如果该节点左子节点不为空，则把该左子节点加入队列，同理右节点也同样处理，当队列不为空时循环队列
    //https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/solution/mian-shi-ti-32-i-cong-shang-dao-xia-da-yin-er-ch-4/
    //题目要求的二叉树的从上至下打印（即按层打印），又称为二叉树的广度优先搜索（BFS）。
    //BFS 通常借助队列的先入先出特性来实现。
    static class Solution {
        /**
         * @author AShuo
         * @date 2021/9/26 15:44
         *
         * 剑指 Offer 32 - II. 从上到下打印二叉树 II
         * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
         *
         * 例如:
         * 给定二叉树: [3,9,20,null,null,15,7],
         *
         *    3
         *   / \
         *  9  20
         * /    \
         * 15    7
         * 返回其层次遍历结果：
         * [
         * [3],
         * [9,20],
         * [15,7]
         * ]
         *
         * 提示：
         * 节点总数 <= 1000
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */

        //用一个for循环，记录当前层数的所有节点数，并把节点值加入到temp列表中，然后for循环退出时，wihle循环会用for循环下一层节点
        //https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/solution/mian-shi-ti-32-ii-cong-shang-dao-xia-da-yin-er-c-5/
        public List<List<Integer>> levelOrder2(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> list = new ArrayList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> tmp = new ArrayList<>();
                //queue.size()=这一层所有left+所有right，当for退出时，queue都是下一层的所有左右子节点,一次for循环就是一层
                for (int i = queue.size(); i > 0; i--) {
                    TreeNode node = queue.poll();
                    tmp.add(node.val);
                    if (node.left != null) {
                        //左子节点不为空，入队列
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        //右子节点不为空，入队列
                        queue.offer(node.right);
                    }
                }
                list.add(tmp);
            }
            return list;
        }

        /**
         * @author AShuo
         * @date 2021/9/26 16:29
         * 剑指 Offer 32 - III. 从上到下打印二叉树 III
         * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
         *
         * 例如:
         * 给定二叉树: [3,9,20,null,null,15,7],
         *
         *     3
         *    / \
         *   9  20
         *  /    \
         * 15     7
         * 返回其层次遍历结果：
         *
         * [
         * [3],
         * [20,9],
         * [15,7]
         * ]
         *
         * 提示：
         * 节点总数 <= 1000
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */

        //加一个状态判断对列表是进行头插还是尾插
        //https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/solution/mian-shi-ti-32-iii-cong-shang-dao-xia-da-yin-er--3/
        public List<List<Integer>> levelOrder3(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> list = new ArrayList<>();
            boolean isLeftToRight = false;
            queue.offer(root);
            while (!queue.isEmpty()) {
                //LinkedList可以头插，尾插元素
                LinkedList<Integer> tmp = new LinkedList<>();
                //queue.size()=这一层所有left+所有right，当for退出时，queue都是下一层的所有左右子节点,一次for循环就是一层
                for (int i = queue.size(); i > 0; i--) {
                    TreeNode node = queue.poll();
                    if (isLeftToRight) {
                        tmp.addFirst(node.val);
                    } else {
                        tmp.addLast(node.val);
                    }
                    if (node.left != null) {
                        //左子节点不为空，入队列
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        //右子节点不为空，入队列
                        queue.offer(node.right);
                    }
                }
                list.add(tmp);
                isLeftToRight = !isLeftToRight;
            }
            return list;
        }

        //加状态，反转列表
        public List<List<Integer>> levelOrder4(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> list = new ArrayList<>();
            boolean isLeftToRight = false;
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> tmp = new ArrayList<>();
                //queue.size()=这一层所有left+所有right，当for退出时，queue都是下一层的所有左右子节点,一次for循环就是一层
                for (int i = queue.size(); i > 0; i--) {
                    TreeNode node = queue.poll();
                    tmp.add(node.val);
                    if (node.left != null) {
                        //左子节点不为空，入队列
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        //右子节点不为空，入队列
                        queue.offer(node.right);
                    }
                }
                if (isLeftToRight) {
                    Collections.reverse(tmp);
                }
                list.add(tmp);
                isLeftToRight = !isLeftToRight;
            }
            return list;
        }

        public int[] levelOrder(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            Queue<TreeNode> queue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            //根节点入队列
            //offer 添加一个元素并返回true 如果队列已满，则返回false
            //poll 移除并返问队列头部的元素 如果队列为空，则返回null
            queue.offer(root);
            while (queue.size() != 0) {
                TreeNode treeNode = queue.poll();
                //把节点值存入到list中
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    //左子节点不为空，入队列
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    //右子节点不为空，入队列
                    queue.offer(treeNode.right);
                }
            }
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }
            return arr;
        }
    }
}
