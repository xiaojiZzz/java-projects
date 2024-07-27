class Main {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] pre = new int[n];
        int[] post = new int[n];
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                pre[i] = pre[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                post[i] = post[i + 1] + 1;
            }
        }
        int ans = n;
        for (int i = 0; i < n; i++) {
            ans += Math.max(pre[i], post[i]);
        }
        return ans;
    }
}