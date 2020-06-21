public class L347 {

    /**
     * 方法1：使用排序取前k个不重复的数字，但是时间复杂度不严格小于等于nlogn;
     * 方法2：用map计数后使用桶排序，取前k个次数出现最多的数字；
     * 方法3：用map计数，使用最小堆维护前k个次数出现最多的数字；
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int a: nums) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for(int key: map.keySet()) {
            if(priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if(map.get(key) > map.get(priorityQueue.peek())) {
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }

        int[] res = new int[k];
        int i = 0;
        while(!priorityQueue.isEmpty()) {
            res[i++] = priorityQueue.remove();
        }
        return res;
    }

}