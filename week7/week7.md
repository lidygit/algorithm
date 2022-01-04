## 完全平方数

```
class Solution {
    public int numSquares(int n) {
        int[] f = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j*j <= i ; j++) {
                min = Math.min(min, f[i-j*j]);
            }
            f[i] = min + 1;
        }
        return f[n];
    }
}
```

 ## 最长回文子序列

```
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n-1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i+1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }

        }
        return dp[0][n-1];
    }
}
```

