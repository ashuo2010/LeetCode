package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author AShuo
 * @date 2021年10月28日 9:57
 * 
 * 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 
 * 示例：
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer37 {
    public static void main(String[] args) {

//                 3
//                / \
//               4   5
//                \   \
//                 2   6

        TreeNode treeNodeA1 = new TreeNode(3);
        TreeNode treeNodeA2 = new TreeNode(4);
        TreeNode treeNodeA3 = new TreeNode(5);
        TreeNode treeNodeA4 = new TreeNode(2);
        TreeNode treeNodeA5 = new TreeNode(6);
        treeNodeA1.left = treeNodeA2;
        treeNodeA1.right = treeNodeA3;
        treeNodeA2.right = treeNodeA4;
        treeNodeA3.right = treeNodeA5;

        System.out.println(new Solution().serialize(treeNodeA1));
        System.out.println(new Solution().deserialize(new Solution().serialize(treeNodeA1)));
    }

    static class Solution {
        //将根节点入队列，之后当队列不为空的时候进行循环，当出队列后的元素不为空的时候，将其左右节点入栈，否则不入栈，进行一次BFS的二叉树层序遍历
        //入栈之前将出对列的值存放到字符串中，最后处理返回即可
        //https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/solution/mian-shi-ti-37-xu-lie-hua-er-cha-shu-ceng-xu-bian-/
        public String serialize(TreeNode root) {
            if (root == null) {
                return "[]";
            }
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("[");
            Queue<TreeNode> queue = new LinkedList();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    sBuilder.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    sBuilder.append("null");
                }
                sBuilder.append(",");
            }
            sBuilder.deleteCharAt(sBuilder.length() - 1);
            sBuilder.append("]");
            return sBuilder.toString();
        }

        //将字符串按","进行分割，创建一个根节点并将其入栈，再将队列的队首元素出栈
        //如果字符串数组对应位置的元素不为空，则先创建一个以该元素为值的节点，并先让其指向父节点的左子节点，再将该创建的节点入栈
        //然后字符串数组往后移动，同理将右子节点进行创建
        public TreeNode deserialize(String data) {
            if (data.equals("[]")) {
                return null;
            }
            String[] strings = data.substring(1, data.length() - 1).split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
            queue.offer(root);
            int i = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!strings[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(strings[i]));
                    queue.offer(node.left);
                }
                i++;
                if (!strings[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(strings[i]));
                    queue.offer(node.right);
                }
                i++;
            }
            return root;
        }
    }
}
