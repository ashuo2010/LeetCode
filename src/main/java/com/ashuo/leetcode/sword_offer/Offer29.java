package com.ashuo.leetcode.sword_offer;

import java.util.Arrays;

/**
 * @author AShuo
 * @date 2021年10月26日 9:53
 * 
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer29 {
    public static void main(String[] args) {
        Arrays.stream(new Solution().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})).forEach(System.out::println);
    }

    static class Solution {
        //定义二维数组上边界top，下边界bottom，左边界left，有边界right，从left出发遍历到right，并把元素添加到新数组中，top再加一，相对于减去了从left到right的元素
        //如果top加一后，小于bottom，说明接下来已经没有元素了可以遍历了，退出循环，同理从top->bottom、right-left、bottom->top也是一样处理
        //https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/mian-shi-ti-29-shun-shi-zhen-da-yin-ju-zhen-she-di/
        public int[] spiralOrder(int[][] matrix) {
            if (matrix.length == 0) {
                return new int[0];
            }
            int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
            int[] res = new int[(bottom + 1) * (right + 1)];
            int index = 0;
            while (true) {
                for (int i = left; i <= right; i++) {
                    res[index++] = matrix[top][i];
                }
                if (++top > bottom) {
                    break;
                }
                for (int i = top; i <= bottom; i++) {
                    res[index++] = matrix[i][right];
                }
                if (--right < left) {
                    break;
                }
                for (int i = right; i >= left; i--) {
                    res[index++] = matrix[bottom][i];
                }
                if (--bottom < top) {
                    break;
                }
                for (int i = bottom; i >= top; i--) {
                    res[index++] = matrix[i][left];
                }
                if (++left > right) {
                    break;
                }
            }
            return res;
        }
    }
}
