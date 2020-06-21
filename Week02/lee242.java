class Solution {

    /**
     * 方法1：使用哈希表，记录字符出现次数，次数不同则不是异位词，时间复杂度：o(n),空间复杂度：o(1)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];
        for(int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < t.length(); i++) {
            int res = --arr[t.charAt(i) - 'a'];
            if(res < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 方法2：排序后判断两字符串是否相等,时间复杂度：o(nlogN),空间复杂度：o(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);

        for(int i = 0; i < s.length(); i++) {
            if(sa[i] != ta[i]) {
                return false;
            }
        }

        return true;
    }
}