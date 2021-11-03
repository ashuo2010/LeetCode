package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年09月28日 14:53
 * 
 * 剑指 Offer 47. 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 
 * 示例 1:
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 * 
 * 提示：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer47 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxValue(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    //动态规划，dp(i,j)=max[dp(i,j−1),dp(i−1,j)]+grid(i,j)，其中dp(i,j)为走到(i,j)位置的最大值
    //https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/solution/mian-shi-ti-47-li-wu-de-zui-da-jie-zhi-dong-tai-gu/
    static class Solution {
        public int maxValue(int[][] grid) {
            int row = grid.length;
            int column = grid[0].length;
            int[][] dp = new int[row + 1][column + 1];
            //从第2行开始，遍历每一行的值。值为max(上一个,左一个)
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= column; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
                }
            }

            //dp:
            //0,0,0,0
            //0,1,4,5
            //0,2,9,10
            //0,6,11,12
            return dp[row][column];
        }
    }
}
