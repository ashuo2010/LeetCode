package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月26日 9:53
 * 
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * 
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer04 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}

        };
        System.out.println(new Solution().findNumberIn2DArray(arr, 5));
        System.out.println(new Solution().findNumberIn2DArray(arr, 20));
    }

    static class Solution {
        //对于任意一个元素来说，上边的一定比本元素小，右边的一定比本元素大，所以从最左下角的元素开始进行遍历，根据目标值进行移动，查找元素
        //https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/solution/mian-shi-ti-04-er-wei-shu-zu-zhong-de-cha-zhao-zuo/
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            //从最左下脚开始遍历
            int i = matrix.length - 1;
            int j = 0;
            //当数组没有越界时
            while (i >= 0 && j < matrix[0].length) {
                //如果该位置大于目标值，上边的元素更小，向上移动
                if (matrix[i][j] > target) {
                    i--;
                } else if (matrix[i][j] < target) {
                    //如果该位置小于目标值，右边的元素更大，向右移动
                    j++;
                } else {
                    //如果相等表示找到了
                    return true;
                }
            }
            return false;
        }
    }
}
