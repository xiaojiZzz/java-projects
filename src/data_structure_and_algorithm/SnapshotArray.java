package data_structure_and_algorithm;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
// 快照数组 lc.1146 用于查找不同版本数组对应索引处的值
public class SnapshotArray {
    private int snap_cnt;
    private List<int[]>[] data;

    public SnapshotArray(int length) {
        snap_cnt = 0;
        data = new List[length];
        for (int i = 0; i < length; i++) {
            data[i] = new ArrayList<int[]>();
        }
    }

    // 将指定索引 index 处的元素设置为 val
    public void set(int index, int val) {
        data[index].add(new int[]{snap_cnt, val});
    }

    // 返回快照的编号 snap_id
    public int snap() {
        return snap_cnt++;
    }

    // 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值
    public int get(int index, int snap_id) {
        int x = binarySearch(index, snap_id);
        return x == 0 ? 0 : data[index].get(x - 1)[1];
    }

    // 二分查找
    private int binarySearch(int index, int snap_id) {
        int low = 0, high = data[index].size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            int[] pair = data[index].get(mid);
            if (pair[0] > snap_id) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
