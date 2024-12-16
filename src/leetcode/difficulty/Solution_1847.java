package leetcode.difficulty;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 最近的房间
 * 一个酒店里有 n 个房间，这些房间用二维整数数组 rooms 表示，
 * 其中 rooms[i] = [roomIdi, sizei] 表示有一个房间号为 roomIdi 的房间且它的面积为 sizei 。每一个房间号 roomIdi 保证是 独一无二 的。
 * 同时给你 k 个查询，用二维数组 queries 表示，其中 queries[j] = [preferredj, minSizej] 。第 j 个查询的答案是满足如下条件的房间 id ：
 * 房间的面积 至少 为 minSizej ，且
 * abs(id - preferredj) 的值 最小 ，其中 abs(x) 是 x 的绝对值。
 * 如果差的绝对值有 相等 的，选择 最小 的 id 。如果 没有满足条件的房间 ，答案为 -1 。
 * 请你返回长度为 k 的数组 answer ，其中 answer[j] 为第 j 个查询的结果。
 * 示例 1：
 * 输入：rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
 * 输出：[3,-1,3]
 * 解释：查询的答案如下：
 * 查询 [3,1] ：房间 3 的面积为 2 ，大于等于 1 ，且号码是最接近 3 的，为 abs(3 - 3) = 0 ，所以答案为 3 。
 * 查询 [3,3] ：没有房间的面积至少为 3 ，所以答案为 -1 。
 * 查询 [5,2] ：房间 3 的面积为 2 ，大于等于 2 ，且号码是最接近 5 的，为 abs(3 - 5) = 2 ，所以答案为 3 。
 * 示例 2：
 * 输入：rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
 * 输出：[2,1,3]
 * 解释：查询的答案如下：
 * 查询 [2,3] ：房间 2 的面积为 3 ，大于等于 3 ，且号码是最接近的，为 abs(2 - 2) = 0 ，所以答案为 2 。
 * 查询 [2,4] ：房间 1 和 3 的面积都至少为 4 ，答案为 1 因为它房间编号更小。
 * 查询 [2,5] ：房间 3 是唯一面积大于等于 5 的，所以答案为 3 。
 * 提示：
 * n == rooms.length
 * 1 <= n <= 105
 * k == queries.length
 * 1 <= k <= 104
 * 1 <= roomIdi, preferredj <= 107
 * 1 <= sizei, minSizej <= 107
 */
public class Solution_1847 {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        int q = queries.length, n = rooms.length;
        Integer[] idx = new Integer[q];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (a, b) -> queries[b][1] - queries[a][1]);
        int[] ans = new int[q];
        Arrays.fill(ans, -1);
        TreeSet<Integer> treeSet = new TreeSet<>();
        int j = 0;
        for (int i : idx) {
            int preferred = queries[i][0];
            int minSize = queries[i][1];
            while (j < n && rooms[j][1] >= minSize) {
                treeSet.add(rooms[j][0]);
                j++;
            }
            int diff = Integer.MAX_VALUE;
            Integer floor = treeSet.floor(preferred);
            if (floor != null) {
                diff = preferred - floor;
                ans[i] = floor;
            }
            if (diff <= 1) {
                continue;
            }
            Integer ceiling = treeSet.ceiling(preferred);
            if (ceiling != null && ceiling - preferred < diff) {
                ans[i] = ceiling;
            }
        }
        return ans;
    }
}

/*
// 另一种写法
class Event implements Comparable<Event> {
    int type, size, id, origin;

    public Event(int type, int size, int id, int origin) {
        this.type = type;
        this.size = size;
        this.id = id;
        this.origin = origin;
    }

    @Override
    public int compareTo(Event that) {
        // 自定义比较函数，按照事件的 size 降序排序
        // 如果 size 相同，优先考虑房间
        if (this.size != that.size) {
            return Integer.compare(that.size, this.size);
        } else {
            return Integer.compare(this.type, that.type);
        }
    }
}

class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int m = rooms.length;
        int n = queries.length;

        // 创建事件列表，存储房间和询问事件
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            // 房间事件
            events.add(new Event(0, rooms[i][1], rooms[i][0], i));
        }
        for (int i = 0; i < n; ++i) {
            // 询问事件
            events.add(new Event(1, queries[i][1], queries[i][0], i));
        }
        // 对事件列表进行排序
        Collections.sort(events);
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        // 使用 TreeSet 存储房间 roomId 的有序集合
        TreeSet<Integer> valid = new TreeSet<>();

        for (Event event : events) {
            if (event.type == 0) {
                // 房间事件，将 roomId 加入有序集合
                valid.add(event.id);
            } else {
                // 询问事件，查找最近的房间
                Integer higher = valid.ceiling(event.id);
                Integer lower = valid.floor(event.id);
                int dist = Integer.MAX_VALUE;

                // 查找最小的大于等于 preferred 的元素
                if (higher != null && higher - event.id < dist) {
                    dist = higher - event.id;
                    ans[event.origin] = higher;
                }
                // 查找最大的严格小于 preferred 的元素
                if (lower != null && event.id - lower <= dist) {
                    ans[event.origin] = lower;
                }
            }
        }

        return ans;
    }
}
*/