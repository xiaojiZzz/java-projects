package data_structure_and_algorithm;


// 线段树 用于：区间求和、单点修改、区间修改、区间最大值
public class SegmentTree {
    private int n;
    private int[] nums;
    private int[] tree;
    // 懒惰标记（增加值）
    private int[] lazyDiff;
    // 懒惰标记（修改值）
    private int[] lazyVal;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[4 * n];
        this.lazyDiff = new int[4 * n];
        this.lazyVal = new int[4 * n];
        build(0, 0, n - 1);
    }

    public void build(int index, int s, int e) {
        // [s, e]为当前index结点包含的区间
        if (s == e) {
            tree[index] = nums[s];
            return;
        }
        int mid = s + (e - s) / 2;
        build(2 * index + 1, s, mid);
        build(2 * index + 2, mid + 1, e);
        tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
    }

    // 区间求和（用于区间值加上某个值时）
    public int sumRangeDiff(int left, int right) {
        return rangeDiff(left, right, 0, 0, n - 1);
    }

    // 区间求和（用于区间值修改成某个值时）
    public int sumRangeVal(int left, int right) {
        return rangeVal(left, right, 0, 0, n - 1);
    }

    // 单点修改（不与区间修改同时用，用区间修改可实现单点修改（left == right）时）
    public void updateOne(int idx, int val) {
        changeOne(idx, val, 0, 0, n - 1);
    }

    // 区间修改（区间值加上val）
    public void updateRangeDiff(int left, int right, int val) {
        changeRangeDiff(left, right, val, 0, 0, n - 1);
    }

    // 区间修改（区间值修改为val）ps: 若两种区间修改同时使用时候需要建立额外的数组存储是否是修改值还是增加值
    public void updateRangeVal(int left, int right, int val) {
        changeRangeVal(left, right, val, 0, 0, n - 1);
    }

    // 区间最大值
    public int queryRangeMax(int left, int right) {
        return getRangeMax(left, right, 0, 0, n - 1);
    }

    public int rangeDiff(int left, int right, int index, int s, int e) {
        // [left, right] 为需要查询的区间，[s, e] 为当前index结点包含的区间
        if (left <= s && right >= e) {
            return tree[index];
        }
        int mid = s + (e - s) / 2;
        // 判断懒惰标记，更新当前结点的两个子结点
        if (lazyDiff[index] != 0) {
            tree[2 * index + 1] += lazyDiff[index] * (mid - s + 1);
            tree[2 * index + 2] += lazyDiff[index] * (e - mid);
            lazyDiff[2 * index + 1] += lazyDiff[index];
            lazyDiff[2 * index + 2] += lazyDiff[index];
            lazyDiff[index] = 0;
        }
        int sum = 0;
        if (left <= mid) {
            sum += rangeDiff(left, right, 2 * index + 1, s, mid);
        }
        if (right >= mid + 1) {
            sum += rangeDiff(left, right, 2 * index + 2, mid + 1, e);
        }
        return sum;
    }

    public int rangeVal(int left, int right, int index, int s, int e) {
        // [left, right] 为需要查询的区间，[s, e] 为当前index结点包含的区间
        if (left <= s && right >= e) {
            return tree[index];
        }
        int mid = s + (e - s) / 2;
        if (lazyVal[index] != 0) {
            tree[2 * index + 1] = lazyDiff[index] * (mid - s + 1);
            tree[2 * index + 2] = lazyDiff[index] * (e - mid);
            lazyDiff[2 * index + 1] = lazyDiff[index];
            lazyDiff[2 * index + 2] = lazyDiff[index];
            lazyDiff[index] = 0;
        }
        int sum = 0;
        if (left <= mid) {
            sum += rangeVal(left, right, 2 * index + 1, s, mid);
        }
        if (right >= mid + 1) {
            sum += rangeVal(left, right, 2 * index + 2, mid + 1, e);
        }
        return sum;
    }

    public void changeOne(int idx, int val, int index, int s, int e) {
        // 更新 nums 数组下标 idx 的值为 val ，[s, e] 为当前 index 结点包含的区间
        if (s == e) {
            tree[index] = val;
            return;
        }
        int mid = s + (e - s) / 2;
        if (idx <= mid) {
            changeOne(idx, val, 2 * index + 1, s, mid);
        } else {
            changeOne(idx, val, 2 * index + 2, mid + 1, e);
        }
        tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
    }

    public void changeRangeDiff(int left, int right, int val, int index, int s, int e) {
        // [left, right] 为需要修改的区间，val 为区间值的变化量，[s, e] 为当前 index 结点包含的区间
        if (left <= s && right >= e) {
            tree[index] += (e - s + 1) * val;
            lazyDiff[index] += val;
            return;
        }
        int mid = s + (e - s) / 2;
        // 判断懒惰标记，更新当前结点的两个子结点
        if (lazyDiff[index] != 0) {
            tree[2 * index + 1] += lazyDiff[index] * (mid - s + 1);
            tree[2 * index + 2] += lazyDiff[index] * (e - mid);
            lazyDiff[2 * index + 1] += lazyDiff[index];
            lazyDiff[2 * index + 2] += lazyDiff[index];
            lazyDiff[index] = 0;
        }
        if (left <= mid) {
            changeRangeDiff(left, right, val, 2 * index + 1, s, mid);
        }
        if (right >= mid + 1) {
            changeRangeDiff(left, right, val, 2 * index + 2, mid + 1, e);
        }
        tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
    }

    public void changeRangeVal(int left, int right, int val, int index, int s, int e) {
        // [left, right] 为需要修改的区间，val 为区间值，[s, e] 为当前 index 结点包含的区间
        if (left <= s && right >= e) {
            tree[index] = (e - s + 1) * val;
            lazyVal[index] = val;
            return;
        }
        int mid = s + (e - s) / 2;
        // 判断懒惰标记，更新当前结点的两个子结点
        if (lazyVal[index] != 0) {
            tree[2 * index + 1] = lazyVal[index] * (mid - s + 1);
            tree[2 * index + 2] = lazyVal[index] * (e - mid);
            lazyVal[2 * index + 1] = lazyVal[index];
            lazyVal[2 * index + 2] = lazyVal[index];
            lazyVal[index] = 0;
        }
        if (left <= mid) {
            changeRangeVal(left, right, val, 2 * index + 1, s, mid);
        }
        if (right >= mid + 1) {
            changeRangeVal(left, right, val, 2 * index + 2, mid + 1, e);
        }
        tree[index] = tree[2 * index + 1] + tree[2 * index + 2];
    }

    public int getRangeMax(int left, int right, int index, int s, int e) {
        // [left, right] 为需要修改的区间，[s, e] 为当前 index 结点包含的区间
        if (left <= s && right >= e) {
            return tree[index];
        }
        int mid = s + (e - s) / 2;
        int maxLeft = 0;
        int maxRight = 0;
        if (left <= mid) {
            maxLeft = getRangeMax(left, right, 2 * index + 1, s, mid);
        }
        if (right >= mid + 1) {
            maxRight = getRangeMax(left, right, 2 * index + 2, mid + 1, e);
        }
        return Math.max(maxLeft, maxRight);
    }
}


// 线段树（动态开点）
class SegmentTreeDynamic {
    class Node {
        Node left, right;
        int val, add;
    }

    // 区间修改
    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.val += (end - start + 1) * val;
            node.add += val;
            return;
        }
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) {
            update(node.left, start, mid, l, r, val);
        }
        if (r > mid) {
            update(node.right, mid + 1, end, l, r, val);
        }
        pushUp(node);
    }

    // 区间求和
    public int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return node.val;
        }
        int mid = (start + end) >> 1;
        int ans = 0;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) {
            ans += query(node.left, start, mid, l, r);
        }
        if (r > mid) {
            ans += query(node.right, mid + 1, end, l, r);
        }
        return ans;
    }

    private void pushUp(Node node) {
        node.val = node.left.val + node.right.val;
    }

    private void pushDown(Node node, int leftNum, int rightNum) {
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.add == 0) {
            return;
        }
        node.left.val += node.add * leftNum;
        node.right.val += node.add * rightNum;
        // 对区间进行「加减」的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }
}
