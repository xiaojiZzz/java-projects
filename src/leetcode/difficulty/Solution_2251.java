package leetcode.difficulty;

import java.util.Arrays;


/**
 * 给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期从 starti 到 endi （都 包含）。
 * 同时给你一个下标从 0 开始大小为 n 的整数数组 people ，people[i] 是第 i 个人来看花的时间。
 * 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
 * 示例 1：
 * 输入：flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
 * 输出：[1,2,2,2]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 * 示例 2：
 * 输入：flowers = [[1,10],[3,3]], people = [3,3,2]
 * 输出：[2,2,1]
 * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
 * 对每个人，我们返回他们到达时在花期内花的数目。
 */
public class Solution_2251 {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int[] ans = new int[people.length];
        int[] start = new int[flowers.length];
        int[] end = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            start[i] = flowers[i][0];
            end[i] = flowers[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for (int i = 0; i < people.length; i++) {
            int l = 0, r = flowers.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (people[i] < start[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] += l;
            l = 0;
            r = flowers.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (people[i] <= end[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] -= l;
        }
        return ans;
    }
}

/*
class Solution {
    //差分
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for (int[] flower : flowers) {
            cnt.put(flower[0], cnt.getOrDefault(flower[0], 0) + 1);
            cnt.put(flower[1] + 1, cnt.getOrDefault(flower[1] + 1, 0) - 1);
        }
        int m = people.length;
        Integer[] indices = IntStream.range(0, m).boxed().toArray(Integer[]::new);
        Arrays.sort(indices, (i, j) -> people[i] - people[j]);
        int[] ans = new int[m];
        int curr = 0;
        for (int x : indices) {
            while (!cnt.isEmpty() && cnt.firstKey() <= people[x]) {
                curr += cnt.pollFirstEntry().getValue();
            }
            ans[x] = curr;
        }
        return ans;
    }
}
*/

/*
class Solution {
    //优先队列
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n=people.length;
        int[] ans=new int[n];
        Integer[] idx=new Integer[n];
        for(int i=0;i<n;i++){
            idx[i]=i;
        }
        Arrays.sort(idx,(o1,o2)->people[o1]-people[o2]);
        Arrays.sort(flowers,(o1,o2)->o1[0]-o2[0]);
        PriorityQueue<Integer> heap=new PriorityQueue<>();
        for(int i=0,j=0;i<n;i++){
            int p=people[idx[i]];
            for(;j<flowers.length && flowers[j][0]<=p;j++){
                heap.offer(flowers[j][1]);
            }
            while(!heap.isEmpty() && heap.peek()<p){
                heap.poll();
            }
            ans[idx[i]]=heap.size();
        }
        return ans;
    }
}
*/
