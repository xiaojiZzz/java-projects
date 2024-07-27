package leetcode.medium;

import java.util.*;


/**
 * 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。你会得到一个数组 prerequisite ，
 * 其中 prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj 的先决条件。
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 * 示例 3：
 * 输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * 输出：[true,true]
 */
public class Solution_1462 {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        //构建一个可达的map表，留着一会bfs用
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] val : prerequisites) {
            HashSet<Integer> set = map.containsKey(val[0]) ? map.get(val[0]) : new HashSet<>();
            set.add(val[1]);
            map.put(val[0], set);
        }

        //创建一个结果集，里面存放所有可达关系
        HashSet<String> res = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            bfser(map, i, numCourses, res);
        }

        //统计答案
        List<Boolean> ans = new ArrayList<>();
        for (int[] val : queries) {
            ans.add(res.contains(val[0] + "_" + val[1]));
        }
        return ans;
    }

    private void bfser(HashMap<Integer, HashSet<Integer>> map, int i, int numCourses, HashSet<String> res) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[numCourses];
        //如果这个值在map中不存在，说明他到其他课程没有可达关系
        if (!map.containsKey(i)) return;
        queue.offer(i);
        //我们的目标就是求root到其他点的可达关系，所以在bfs过程中，记录下这个可达关系，放在set里，这里
        //我用 a_b 这种字符串形式来记录了。
        visited[i] = true;
        while (!queue.isEmpty()) {
            HashSet<Integer> data = map.get(queue.poll());
            if (data != null) {
                for (Integer d : data) {
                    if (!visited[d]) {
                        res.add(i + "_" + d);
                        queue.offer(d);
                        visited[d] = true;
                    }
                }
            }
        }
        //遍历结束后，所有可达的线都可以记录下来，求完所有的课程即可形成一个最终的统计集合，就可以遍历一遍查询数据
        //来返回结果了。
    }
}

/*
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        int[] indgree = new int[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            ++indgree[p[1]];
            g[p[0]].add(p[1]);
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indgree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int ne : g[cur]) {
                isPre[cur][ne] = true;
                for (int i = 0; i < numCourses; ++i) {
                    isPre[i][ne] = isPre[i][ne] | isPre[i][cur];
                }
                --indgree[ne];
                if (indgree[ne] == 0) {
                    queue.offer(ne);
                }
            }
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }
}
*/

/*
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        boolean[] vi = new boolean[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            g[p[0]].add(p[1]);
        }
        for (int i = 0; i < numCourses; ++i) {
            dfs(g, isPre, vi, i);
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }

    public void dfs(List<Integer>[] g, boolean[][] isPre, boolean[] vi, int cur) {
        if (vi[cur]) {
            return;
        }
        vi[cur] = true;
        for (int ne : g[cur]) {
            dfs(g, isPre, vi, ne);
            isPre[cur][ne] = true;
            for (int i = 0; i < isPre.length; ++i) {
                isPre[cur][i] = isPre[cur][i] | isPre[ne][i];
            }
        }
    }
}
*/

/*
class Solution {
    private boolean[][] arr;

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        arr = new boolean[n][n];
        for (int[] prerequisite : prerequisites) {
            arr[prerequisite[0]][prerequisite[1]] = true;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][prerequisite[0]]) {
                    arr[i][prerequisite[1]] = true;
                }
                if (arr[prerequisite[1]][i]) {
                    arr[prerequisite[0]][i] = true;
                }
            }
        }
        List<Boolean> result = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            result.add(arr[query[0]][query[1]]);
        }
        return result;
    }
}
*/

/*
class Solution {
    // Floyd
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] f = new boolean[n][n];
        for (var p : prerequisites) {
            f[p[0]][p[1]] = true;
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    f[i][j] |= f[i][k] && f[k][j];
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (var q : queries) {
            ans.add(f[q[0]][q[1]]);
        }
        return ans;
    }
}
*/
