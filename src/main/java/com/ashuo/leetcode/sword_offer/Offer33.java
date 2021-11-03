package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月20日 16:14
 *
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 * 参考以下这颗二叉搜索树：
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 *
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 *
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 * 提示：
 * 数组长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer33 {
    public static void main(String[] args) {
        System.out.println(new Solution().verifyPostorder(new int[]{1, 6, 3, 2, 5}));
        System.out.println(new Solution().verifyPostorder(new int[]{1, 3, 2, 6, 5}));
    }

    static class Solution {
        public boolean verifyPostorder(int[] postorder) {
            return recur(postorder, 0, postorder.length - 1);
        }

        //因为是后序遍历，所以数组最后一个是根节点，遍历数组，找到第一个比根节点大的元素，此时该元素的左边都比该元素小，即是左子树的所有元素都满足条件
        //此时还需要判断右边的子树，可以通过一个计数p，让p从最左边出发，当遇到条件时（遇到一个postorder[p] < postorder[right]）,则退出，此时p!=j，表示该m节点不符合条件，不是后序遍历的二叉搜索树
        //https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
        public boolean recur(int[] postorder, int left, int right) {
            //当left>=right时，树的节点数量≤1,无需判断，直接返回
            if (left >= right) {
                return true;
            }
            //遍历找到第一个大于根节点（因为是后序遍历，所以根节点在数组最后面）的索引位置m,
            int p = left;
            while (postorder[p] < postorder[right]) {
                p++;
            }
            int m = p;
            while (postorder[p] > postorder[right]) {
                p++;
            }
            //判断p走的路径和i~j的距离是否相同，如果相同则（left,m-1）是左子树,（m,right-1）是右子树，如果不相同则代表第二个while循环遇到了不符合条件的情况，提前退出了
            return p == right && recur(postorder, left, m - 1) && recur(postorder, m, right - 1);
        }
    }
}
