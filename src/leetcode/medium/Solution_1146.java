package leetcode.medium;

import java.util.*;


/**
 * 实现支持下列接口的「快照数组」- SnapshotArray：
 * SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
 * void set(index, val) - 会将指定索引 index 处的元素设置为 val。
 * int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
 * int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
 * 示例：
 * 输入：["SnapshotArray","set","snap","set","get"]
 *      [[3],[0,5],[],[0,6],[0,0]]
 * 输出：[null,null,0,null,5]
 * 解释：
 * SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
 * snapshotArr.set(0,5);  // 令 array[0] = 5
 * snapshotArr.snap();  // 获取快照，返回 snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
 */
public class Solution_1146 {
    class SnapshotArray {

        private int snap_cnt;
        private List<int[]>[] data;

        public SnapshotArray(int length) {
            snap_cnt = 0;
            data = new List[length];
            for (int i = 0; i < length; i++) {
                data[i] = new ArrayList<int[]>();
            }
        }

        public void set(int index, int val) {
            data[index].add(new int[]{snap_cnt, val});
        }

        public int snap() {
            return snap_cnt++;
        }

        public int get(int index, int snap_id) {
            int x = binarySearch(index, snap_id);
            return x == 0 ? 0 : data[index].get(x - 1)[1];
        }

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
}
