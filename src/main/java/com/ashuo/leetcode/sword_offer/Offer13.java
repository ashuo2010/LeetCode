package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月12日 11:47
 * 
 * 剑指 Offer 13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * 
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer13 {
    public static void main(String[] args) {
        System.out.println(new Solution().movingCount(2, 3, 1));
    }

    static class Solution {
        public int movingCount(int m, int n, int k) {
            //用visited标识该位置是否已经访问过
            boolean[][] visited = new boolean[m][n];
            return dfs(visited, m, n, k, 0, 0);
        }

        //DFS算法，从左上角出发，从下边和右边遍历数组，在遍历的过程中判断是否越界和该位置是否符合要求，并标记该位置已访问，防止回溯后重复访问
        //如执行(i+1,j)->(i,j+1)移动且回溯之后，(i,j+1)->(i+1,j)与之前访问的元素会相同
        //https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/submissions/
        public int dfs(boolean[][] visited, int m, int n, int k, int i, int j) {
            //如果数组越界或位数和大于目标值，返回false
            if (i >= m || j >= n || visited[i][j] == true || bitSum(i) + bitSum(j) > k) {
                return 0;
            }
            //设置该位置已访问
            visited[i][j] = true;
            //返回本身这次移动和其下移动+右移动
            return 1 + dfs(visited, m, n, k, i + 1, j) + dfs(visited, m, n, k, i, j + 1);
        }

        //计算位数和
        public int bitSum(int x) {
            int sum = 0;
            while (x != 0) {
                sum += x % 10;
                x = x / 10;
            }
            return sum;
        }
    }
}
