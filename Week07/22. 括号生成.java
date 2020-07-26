class Solution {

    private List<String> ans;

    public List<String> generateParenthesis(int n) {
        if(n <= 0) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        backTrace(2*n, 0, 0, "");
        return ans;
    }

    private void backTrace(int rest, int left, int right, String s) {
        if(rest < 0) {
            return ;
        }
        if(rest == 0 && left == right) {
            ans.add(s);
            return ;
        }

        if(left > right) {
            backTrace(rest - 1, left, right + 1, s + ")");
        }

        backTrace(rest - 1, left + 1, right, s + "(");
    }
}