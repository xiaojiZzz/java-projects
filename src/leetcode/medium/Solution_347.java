package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;


/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 */
public class Solution_347 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int[][] times = new int[max - min + 1][2];
        for (int num : nums) {
            times[num - min][0] = num;
            times[num - min][1] = map.get(num);
        }
        Arrays.sort(times, (a, b) -> b[1] - a[1]);
        for (int i = 0; i < k; i++) {
            ans[i] = times[i][0];
        }
        return ans;
    }
}

/*
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>((m, n) -> m[1] - n[1]);
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
}
*/

/*
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        // 获取每个数字出现次数
        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            // 使用双指针把不小于基准值的元素放到左边，
            // 小于基准值的元素放到右边
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            // 前 k 大的值在左侧的子数组里
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            // 前 k 大的值等于左侧的子数组全部元素
            // 加上右侧子数组中前 k - (index - start + 1) 大的值
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }
}
*/

/*
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<int[]> numCnt = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int num = entry.getKey(), cnt = entry.getValue();
            numCnt.add(new int[]{num, cnt});
        }
        List<int[]> topKs = findTopK(numCnt, k, 0, count.size() - 1);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topKs.get(i)[0];
        }
        return res;
    }

    private List<int[]> findTopK(List<int[]> numCnt, int k, int low, int high) {
        int pivot = low + (int) (Math.random() * (high - low + 1));
        Collections.swap(numCnt, low, pivot);
        int base = numCnt.get(low)[1];
        int i = low;
        for (int j = low + 1; j <= high; j++) {
            if (numCnt.get(j)[1] > base) {
                Collections.swap(numCnt, i + 1, j);
                i++;
            }
        }
        Collections.swap(numCnt, low, i);
        if (i == k - 1) {
            return numCnt.subList(0, k);
        } else if (i > k - 1) {
            return findTopK(numCnt, k, low, i - 1);
        } else {
            return findTopK(numCnt, k, i + 1, high);
        }
    }
}
*/
