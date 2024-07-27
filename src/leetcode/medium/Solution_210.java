package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi]，
 * 表示在选修课程 ai 前 必须 先选修 bi 。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3：
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 */
public class Solution_210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] cnt = new int[numCourses];
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            arrayLists.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            arrayLists.get(prerequisite[1]).add(prerequisite[0]);
            cnt[prerequisite[0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (cnt[i] == 0) {
                queue.add(i);
            }
        }
        int id = 0;
        while (!queue.isEmpty()) {
            numCourses--;
            Integer poll = queue.poll();
            ans[id++] = poll;
            for (Integer integer : arrayLists.get(poll)) {
                if (--cnt[integer] == 0) {
                    queue.add(integer);
                }
            }
        }
        return numCourses == 0 ? ans : new int[]{};
    }
}

/*
class Solution {
    private int k = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        int[] ans = new int[numCourses];
        List[] edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int[] pre : prerequisites) {
            in[pre[0]]++;
            edges[pre[1]].add(pre[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                ans[k++] = i;
                in[i] = -1;
                topologicalSorting(edges, edges[i], in, ans);
            }
        }
        return k == numCourses ? ans : new int[0];
    }

    public void topologicalSorting(List[] edges, List<Integer> list, int[] in, int[] ans) {
        for (int course : list) {
            in[course]--;
            if (in[course] == 0) {
                ans[k++] = course;
                in[course] = -1;
                topologicalSorting(edges, edges[course], in, ans);
            }
        }
    }
}
*/
