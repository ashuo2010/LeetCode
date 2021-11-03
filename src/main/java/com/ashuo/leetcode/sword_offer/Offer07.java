package com.ashuo.leetcode.sword_offer;

import com.ashuo.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AShuo
 * @date 2021年10月20日 14:40
 * 
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * 
 * 限制：
 * 0 <= 节点个数 <= 5000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer07 {
    public static void main(String[] args) {
//                 3
//               /   \
//              9     20
//                   /   \
//                  15    7
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        System.out.println(new Solution().buildTree(pre, in));
    }

    static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            //把中序遍历结果保存到map<节点值，中序索引下标位置>中，方便使用节点值，获取在中序索引的索引位置
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return recur(0, 0, inorder.length - 1, preorder, map);
        }

        //前序遍历的第一个节点既是整个树的根节点
        //通过前序遍历的根节点，可以将中序遍历分为两部分，即 左子树--前序遍历的根节点--右子树
        //https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-di-gui-fa-qin/
        public TreeNode recur(int preRootIndex, int inLeftIndex, int inRightIndex, int[] preorder, Map<Integer, Integer> map) {
            //preRootIndex 前序遍历根节点数组的索引
            //inLeftIndex  中序遍历最左边节点的索引
            //inRightIndex 中序遍历最右边节点的索引

            //如果左边界大于有边界时，到达了叶子节点的下一个节点
            if (inLeftIndex > inRightIndex) {
                return null;
            }
            //构建根节点
            TreeNode root = new TreeNode(preorder[preRootIndex]);
            //获取当前根节点在中序遍历中的数组下标
            int inRootIndex = map.get(preorder[preRootIndex]);
            //构建左子树（左子树根节点，左子树最左边节点，左子树最右边节点），其中左子树根节点=前序遍历的根节点索引加一
            root.left = recur(preRootIndex + 1, inLeftIndex, inRootIndex - 1, preorder, map);
            //构建右子树（右子树根节点=，右子树最左边节点，右子树最右边节点），其中右子树根节点=前序遍历的根节点索引-左子树的节点数量(inRootIndex - inLeftIndex)再加上一既是右根节点
            root.right = recur(preRootIndex + (inRootIndex - inLeftIndex) + 1, inRootIndex + 1, inRightIndex, preorder, map);
            return root;
        }
    }
}
