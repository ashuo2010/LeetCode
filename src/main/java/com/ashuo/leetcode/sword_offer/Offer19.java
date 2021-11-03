package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月29日 10:33
 * 
 * 剑指 Offer 19. 正则表达式匹配
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * 
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer19 {
    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab", "c*a*b"));
        System.out.println(new Solution().isMatch("ab", ".*"));
    }

    static class Solution {
        /*
            状态数组：设二维数组dp[m+1][n+1]，m和n是s、p的长度
                特殊说明：dp[i][j]表示s下标为s[i-1]的字符，p下标为p[j-1]字符
            初始化： dp[i][j] 表示 s的前i个字符和p的前j个字符是否匹配
                dp[0][0]=true，表示s和p的前0个字符均为空串肯定匹配
                若s是空串且p 的偶数次下标为$*$,那也是匹配的
            状态转移：
            p.charAt(j - 1) == '*',有三种情况匹配
                dp[i][j - 2]，既是p[j-2]出现0次
                dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)，p[j-2]出现1次 且 当前i-1和j-2指向的字符相同
                dp[i - 1][j] && p.charAt(j - 2) == '.'，最特殊情况:p[j-2]=. p[j-1]=*时，根据条件知是万能匹配
            p.charAt(j - 1) != '*',有两种情况匹配
                dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)，前面元素之前都匹配 且 当前元素也相容
                dp[i - 1][j - 1] && p.charAt(j - 1) == '.'，前面元素之前都匹配 且 p的当期元素是.
            返回值：dp[m][n]
            */
        //https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/jian-zhi-offer-19-zheng-ze-biao-da-shi-pi-pei-dong/
        public boolean isMatch(String s, String p) {
            int m = s.length() + 1;
            int n = p.length() + 1;
            boolean[][] dp = new boolean[m][n];
            dp[0][0] = true;
            for (int j = 2; j < n; j = j + 2) {
                dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    //如果是*
                    if (p.charAt(j - 1) == '*') {
                        //把*看作零次
                        if (dp[i][j - 2]) {
                            dp[i][j] = true;
                        } else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) {
                            //把*看作一次,且当前的s上的字符与上一个p的字符相等
                            dp[i][j] = true;
                        } else if (dp[i - 1][j] && p.charAt(j - 2) == '.') {
                            //把*看作一次，且上一个p字符是万能的'.'字符
                            dp[i][j] = true;
                        }
                    } else {
                        //如果不是*
                        //如果前面的字符匹配，且当前的两个字符也匹配
                        if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) {
                            dp[i][j] = true;
                        } else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') {
                            //如果前面的字符匹配，且当前p的字符为'.'
                            dp[i][j] = true;
                        }
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
