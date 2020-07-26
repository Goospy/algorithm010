class Solution {

    private boolean[] visited;

    private int[][] gu = {{0, 1}, {1, 0}};

    public int findCircleNum(int[][] M) {
        if(null == M || 0 == M.length) {
            return 0;
        }

        int ans = 0;
        visited = new boolean[M.length];
        for(int i = 0; i < M.length; i++) {
            if(!visited[i] && 0 != M[i][j]) {
                ans++;
                dfs(M, i, j);
            }
        }
        return ans;
    }

    private void dfs(int[][] M, int x) {
        visited[x] = true;
        for(int[] di: gu) {
            int newX = x + di[0];
            int newY = y + di[1];
            if(0 <= newX && newX < M.length && 0 <= newY && newY < M.length && !visited[newX][newY] && 0 != M[newX][newY]) {
                dfs(M, newX, newY);
            }
        }
    }

}