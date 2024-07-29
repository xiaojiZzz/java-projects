package data_structure_and_algorithm;

// 树状数组 用于：区间求和、单点修改、区间最大值（模版要改，可改用线段数）
public class BinaryIndexedTree {
    private int n;
    // 原数组
    private int[] nums;
    // 树状数组 从 1 开始记录
    private int[] tree;

    public BinaryIndexedTree(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    public int lowBit(int x) {
        return x & -x;
    }

    public void add(int x, int val) {
        while (x <= n) {
            tree[x] += val;
            x += lowBit(x);
        }
    }

    public int prefixSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += tree[x];
            x -= lowBit(x);
        }
        return sum;
    }

    // 单点修改
    public void update(int i, int val) {
        add(i + 1, val - nums[i]);
        nums[i] = val;
    }

    //区间求和
    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }
}
