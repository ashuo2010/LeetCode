package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月12日 10:17
 * 
 * 剑指 Offer 12. 矩阵中的路径
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 
 * 提示：
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer12 {
    public static void main(String[] args) {
        System.out.println(new Solution().exist(new char[][]{{'a', 'b'}, {'c', 'd'}}, "abcd"));
    }

    static class Solution {
        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    //题意：从数组任意位置开始遍历，k=0代表从word的第一个字符开始查找
                    if (dfs(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        //深度优先搜索(DFS),DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索,DFS遍历方向为上下左右，在遍历的过程中对已遍历的元素进行标记，防止重复遍历
        //当数组越界或者字符不符合时，进行回溯，再次换一个方向遍历
        //https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/solution/mian-shi-ti-12-ju-zhen-zhong-de-lu-jing-shen-du-yo/
        public boolean dfs(char[][] board, int i, int j, String word, int k) {
            //如果数组越界或者该位置的字符不符合，返回false
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(k)) {
                return false;
            }
            //如果已经遍历到了word的全部字符串，直接返回true，放在后面是防止board[i][j]判断相等时产生数组越界问题
            if (k == word.length() - 1) {
                return true;
            }
            //之所以让board[i][j]标记，是为了防止重复遍历已经遍历的元素，比如在遍历的过程中可能回头遍历获取绕了一圈遍历
            board[i][j] = '\0';
            //上下左右路径探索
            boolean res = dfs(board, i - 1, j, word, k + 1) || dfs(board, i + 1, j, word, k + 1)
                    || dfs(board, i, j - 1, word, k + 1) || dfs(board, i, j + 1, word, k + 1);
            //回复标记的值，因为回溯之后可能还要用到
            board[i][j] = word.charAt(k);
            return res;
        }
    }
}
