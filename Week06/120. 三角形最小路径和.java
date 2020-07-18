class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(null == triangle || 0 == triangle.size()) {
            return 0;
        }

        List<Integer> lowest = triangle.get(triangle.size() - 1);
        for(int j = triangle.size() - 2; j >= 0; j--) {
            List<Integer> cur = triangle.get(j);
            for(int k = 0; k < cur.size(); k++) {
                lowest.set(k, cur.get(k) + Math.min(lowest.get(k), lowest.get(k + 1)));
            }
        }

        return lowest.get(0);
    }
}