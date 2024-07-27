package leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。
 * 如果无法完成此基因变化，返回 -1 。
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * 示例 1：
 * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * 输出：1
 * 示例 2：
 * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 输出：2
 * 示例 3：
 * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 输出：3
 */
public class Solution_433 {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.stream(bank).collect(Collectors.toList()));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(startGene);
        visited.add(startGene);
        int ans = 0;
        char[] letter = new char[]{'A', 'C', 'G', 'T'};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(endGene)) {
                    return ans;
                }
                char[] chars = poll.toCharArray();
                for (int j = 0; j < 8; j++) {
                    char c = chars[j];
                    for (int k = 0; k < 4; k++) {
                        if (c != letter[k]) {
                            chars[j] = letter[k];
                            String s = String.valueOf(chars);
                            if (set.contains(s) && !visited.contains(s)) {
                                visited.add(s);
                                queue.offer(s);
                            }
                            chars[j] = c;
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}
