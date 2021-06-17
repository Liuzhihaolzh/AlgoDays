package AlgoLeet.StringEx;

import java.util.Arrays;
import java.util.List;

public class Solutions {

    // q10
    /**
     *  regular express match: * and ?
     *  思路一：基于递归的方法，
     * @param s
     * @param p
     * @return
     */
    private static boolean isMatchS1(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() == 1) {
            return s.length() == 1 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        }

        if (p.charAt(1) != '*') {
            if (s.isEmpty()) return false;
            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatchS1(p.substring(1), s.substring(1));
        }
        while (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
            if (isMatchS1(s, p.substring(2))) return true;
            s = s.substring(1);
        }
        return isMatchS1(s, p.substring(2));
    }

    private static boolean isMatchS2(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatchS2(s, p.substring(2)) ||
                    (!s.isEmpty() && ((s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                            && isMatchS2(s.substring(1), p)));
        }
        return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                && isMatchS2(s.substring(1), p.substring(1));
    }

    private static boolean matches(String s, String p, int i, int j) {
        if (i == 0) return false;
        if (p.charAt(j-1) == '.') return true;
        return p.charAt(j - 1) == s.charAt(i-1);
    }

    private static boolean isMatchS3(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];
                    if (matches(s, p, i, j-1)) {
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    // q32
    private static boolean wildCartMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n ; ++j) {
                if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else if (p.charAt(j-1) == '?' || p.charAt(j-1) == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }



    public static int longestValidParenthses(String s) {
        if (s.isEmpty()) return 0;
        int[] dp = new int[s.length() + 1];
        int maxlen = 0;
        for (int i = 2; i <= s.length(); ++i) {
            if (s.charAt(i-1) == ')') {
                if (s.charAt(i-2) == '(') {
                    dp[i] = dp[i-2] + 2;
                } else if (i - dp[i-1] >= 0 && s.charAt(i - dp[i-1]) == '(') {
                    dp[i] = dp[i-1] + 2;
                }
            }
            maxlen = maxlen > dp[i] ? maxlen : dp[i];
        }
        return maxlen;
    }

    //
    private static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] <= nums.length && nums[i] > 0) {
                if (nums[i]-1 < 0) continue;;
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
                if (temp < nums.length && temp > 0) {
                    nums[temp-1] = temp;
                }
            }
        }
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i-1] != i) return i;
        }
        return nums.length;
    }

    private static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m == 0 || n == 0) {
            return m + n;
        }

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {

    }
}
