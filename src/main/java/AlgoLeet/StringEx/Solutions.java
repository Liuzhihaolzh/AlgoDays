package AlgoLeet.StringEx;

import java.util.List;

public class Solutions {

    /**
     *  regular express match: * and ?
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        return isMatchS2(s, p);
    }

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

    private static boolean isMatchS3(String s, String p) {
        // dp method
        if (p.isEmpty()) return s.isEmpty();

        int sL = s.length();
        int pL = p.length();
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        boolean[][] dp = new boolean[sL+1][pL+1];
        // INIT
        dp[0][0] = true;
        for (int i = 1; i <= pL; ++i) {
            if (pc[i-1] == '*' && dp[0][i-2]) {
                dp[0][i] = true;
            }
        }
        return false;
    }

    public int largestRectangleArea(List<Integer> vector) {
        return 0;
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        boolean t = isMatch(s, p);
        System.out.println(t);
    }
}
