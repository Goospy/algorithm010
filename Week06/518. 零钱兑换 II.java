class Solution {
    public int change(int amount, int[] coins) {
        if(amount <= 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int coin: coins) {
            fot(int i = coin; i < amount + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}