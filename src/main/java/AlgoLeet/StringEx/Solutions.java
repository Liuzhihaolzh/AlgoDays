package AlgoLeet.StringEx;

import java.util.List;

public class Solutions {

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

    public int largestRectangleArea(List<Integer> vector) {
        return 0;
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        boolean t = isMatchS3(s, p);
        System.out.println(t);
    }
}
