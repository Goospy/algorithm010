class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if(1 == obstacleGrid[0][0]) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(1 == obstacleGrid[i][j]) {
                    continue;
                }
                if(i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if(i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if(i != 0 && j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}