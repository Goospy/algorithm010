
public class lee49 {

    /**
     * 使用排序后的字符串作为key来设置集合
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars);
            if(!map.containsKey(sortedStr)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sortedStr, list);
            } else {
                map.get(sortedStr).add(s);
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 使用字符统计后的字符串作为key来设置集合
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for(String s: strs) {
            Arrays.fill(count, 0);
            for(char c: s.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for(int i: count) {
                stringBuilder.append(count[i]);
            }

            String sortedStr = stringBuilder.toString();
            if(!map.containsKey(sortedStr)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sortedStr, list);
            } else {
                map.get(sortedStr).add(s);
            }
        }
        return new ArrayList<>(map.values());
    }


}
