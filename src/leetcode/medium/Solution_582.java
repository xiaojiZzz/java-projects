package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统中存在 n 个进程，形成一个有根树结构。给你两个整数数组 pid 和 ppid ，
 * 其中 pid[i] 是第 i 个进程的 ID ，ppid[i] 是第 i 个进程的父进程 ID 。
 * 每一个进程只有 一个父进程 ，但是可能会有 一个或者多个子进程 。只有一个进程的 ppid[i] = 0 ，意味着这个进程 没有父进程 。
 * 当一个进程 被杀掉 的时候，它所有的子进程和后代进程都要被杀掉。
 * 给你一个整数 kill 表示要杀掉进程的 ID ，返回被杀掉的进程的 ID 列表。可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * 输出：[5,10]
 * 解释：涂为红色的进程是应该被杀掉的进程。
 * 示例 2：
 * 输入：pid = [1], ppid = [0], kill = 1
 * 输出：[1]
 */
public class Solution_582 {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int fa = ppid.get(i);
            int son = pid.get(i);
            if (map.containsKey(fa)) {
                List<Integer> list = map.get(fa);
                list.add(son);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(son);
                map.put(fa, list);
            }
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(kill);
        List<Integer> list = map.get(kill);
        if (list != null) {
            for (Integer integer : list) {
                ans.addAll(dfs(map, integer));
            }
        }
        return ans;
    }

    public List<Integer> dfs(Map<Integer, List<Integer>> map, int kill) {
        List<Integer> ret = new ArrayList<>();
        ret.add(kill);
        List<Integer> list = map.get(kill);
        if (list != null) {
            for (Integer integer : list) {
                ret.addAll(dfs(map, integer));
            }
        }
        return ret;
    }
}
