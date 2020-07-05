#####寻找旋转排序数组中的最小值
```java
/** 最小值的特征分为两种：
    1.数组是正序未旋转，就是最左元素
    2.数组是旋转后非正序，当前元素下标为i,元素i-1大于元素i则元素i为最小元素

    所以左右指针的变化规则：当mid元素大于left，取右区间，否则取左区间
*/

    public int findMin(int[] nums) {
        if(null == nums) {
            return -1;
        }

        int len = nums.length;
        if(1 == len) {
            return nums[0];
        }

        int left = 0, right = len - 1;
        if(nums[left] < nums[right]) {
            return nums[0];
        }

        while(left < right) {
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            if(nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            if(nums[mid] > nums[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
```

#####单词接龙 II
```java
/**
    应使用图的广度优先遍历，其中双向广度优先遍历时间复杂度最优，可以达到降幂的效果，代码是copy的题解，自己手写的最后没有完成。。
*/
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return res;
        }
        // 第 1 步：使用双向广度优先遍历得到后继结点列表 successors
        // key：字符串，value：广度优先遍历过程中 key 的后继结点列表
        Map<String, Set<String>> successors = new HashMap<>();
        boolean found = bidirectionalBfs(beginWord, endWord, wordSet, successors);
        if (!found) {
            return res;
        }
        // 第 2 步：基于后继结点列表 successors ，使用回溯算法得到所有最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfs(beginWord, endWord, successors, path, res);
        return res;
    }

    private boolean bidirectionalBfs(String beginWord,
                                     String endWord,
                                     Set<String> wordSet,
                                     Map<String, Set<String>> successors) {
        // 记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int wordLen = beginWord.length();
        boolean forward = true;
        boolean found = false;
        // 在保证了 beginVisited 总是较小（可以等于）大小的集合前提下，&& !endVisited.isEmpty() 可以省略
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 一直保证 beginVisited 是相对较小的集合，方便后续编码
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;

                // 只要交换，就更改方向，以便维护 successors 的定义
                forward = !forward;
            }
            Set<String> nextLevelVisited = new HashSet<>();
            // 默认 beginVisited 是小集合，因此从 beginVisited 出发
            for (String currentWord : beginVisited) {
                char[] charArray = currentWord.toCharArray();
                for (int i = 0; i < wordLen; i++) {
                    char originChar = charArray[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (charArray[i] == j) {
                            continue;
                        }
                        charArray[i] = j;
                        String nextWord = new String(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) {
                                found = true;
                                // 在另一侧找到单词以后，还需把这一层关系添加到「后继结点列表」
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }

                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }
                        }
                    }
                    charArray[i] = originChar;
                }
            }
            beginVisited = nextLevelVisited;
            visited.addAll(nextLevelVisited);
            if (found) {
                break;
            }
        }
        return found;
    }

    private void dfs(String beginWord,
                     String endWord,
                     Map<String, Set<String>> successors,
                     Deque<String> path,
                     List<List<String>> res) {

        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (!successors.containsKey(beginWord)) {
            return;
        }

        Set<String> successorWords = successors.get(beginWord);
        for (String successor : successorWords) {
            path.addLast(successor);
            dfs(successor, endWord, successors, path, res);
            path.removeLast();
        }
    }

    private void addToSuccessors(Map<String, Set<String>> successors, boolean forward,
                                 String currentWord, String nextWord) {
        if (!forward) {
            String temp = currentWord;
            currentWord = nextWord;
            nextWord = temp;
        }

        successors.computeIfAbsent(currentWord, a -> new HashSet<>());
        successors.get(currentWord).add(nextWord);
    }

```


#####跳跃游戏 II
```java
/**
    只需要记录i位置元素所能跳到的最右位置，当后续元素不能跳跃超过当前最右位置时，不算跳跃次数，超越时更新最右位置和跳跃次数+1；
*/

    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0; 
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]); 
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
```


