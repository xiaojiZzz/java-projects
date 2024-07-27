package leetcode.lcr;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * 同 leetcode 380. O(1) 时间插入、删除和获取随机元素
 */
public class Solution_030 {
    class RandomizedSet {

        int[] nums = new int[200010];
        Random random = new Random();
        Map<Integer, Integer> map = new HashMap<>();
        int idx = -1;

        public RandomizedSet() {
            //也可以在这进行属性初始化
        }

        public boolean insert(int val) {
            if (map.containsKey(val))
                return false;
            nums[++idx] = val;
            map.put(val, idx);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val))
                return false;
            int index = map.remove(val);
            if (index != idx) {
                map.put(nums[idx], index);
            }
            nums[index] = nums[idx--];
            return true;
        }

        public int getRandom() {
            return nums[random.nextInt(idx + 1)];
        }
    }
}
