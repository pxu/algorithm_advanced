package dynamic_programming.backpack;

public class BackpackII {
    /**
     * 12/26
     * Backpack DP
     *
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        if (m <= 0 || A.length == 0) {
            return 0;
        }

        int a = A.length;
        int[][] dp = new int[2][m + 1];

        for (int i = 1; i <= a; i++) {
            for (int j = 0; j <= m; j++) {
                if (j < A[i - 1]) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                    continue;
                }

                dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[(i - 1) % 2][j - A[i - 1]] + V[i - 1]);
            }
        }

        return dp[a % 2][m];
    }
}
