package leetcode.lcr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Solution_083 {
    //回溯法
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(nums[i]);
        }
        backtrack(lists, list, 0, len);
        return lists;
    }

    public void backtrack(List<List<Integer>> lists, List<Integer> list, int first, int len) {
        if (first == len) {
            lists.add(new ArrayList<Integer>(list));
        }
        for (int i = first; i < len; i++) {
            Collections.swap(list, first, i);
            backtrack(lists, list, first + 1, len);
            Collections.swap(list, first, i);
        }
    }
}

/*class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums,path,ans);
        return ans;
    }

    public void dfs(int[] nums, Deque<Integer> path,List<List<Integer>> ans){
        if(path.size()==nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(!path.contains(nums[i])){
                path.add(nums[i]);
                dfs(nums,path,ans);
                path.removeLast();
            }
        }
    }
}*/

