package leetcode.simple;

/**
 * 给你两个整数 red 和 blue，分别表示红色球和蓝色球的数量。
 * 你需要使用这些球来组成一个三角形，满足第 1 行有 1 个球，第 2 行有 2 个球，第 3 行有 3 个球，依此类推。
 * 每一行的球必须是 相同 颜色，且相邻行的颜色必须 不同。
 * 返回可以实现的三角形的 最大 高度。
 * 示例 1：
 * 输入： red = 2, blue = 4
 * 输出： 3
 * 解释：
 * 上图显示了唯一可能的排列方式。
 * 示例 2：
 * 输入： red = 2, blue = 1
 * 输出： 2
 * 解释：
 * 上图显示了唯一可能的排列方式。
 * 示例 3：
 * 输入： red = 1, blue = 1
 * 输出： 1
 * 示例 4：
 * 输入： red = 10, blue = 1
 * 输出： 2
 * 解释：
 * 上图显示了唯一可能的排列方式。
 * 提示：
 * 1 <= red, blue <= 100
 */
public class Solution_3200 {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(getHeight(red, blue), getHeight(blue, red));
    }

    private int getHeight(int red, int blue) {
        int first = red, second = blue;
        int line = 1, idx = 0, height = 0;
        while (first >= 0 && second >= 0) {
            if (idx == 0) {
                if (first - line >= 0) {
                    first -= line;
                    line++;
                    height++;
                    idx = (idx + 1) % 2;
                } else {
                    break;
                }
            } else {
                if (second - line >= 0) {
                    second -= line;
                    line++;
                    height++;
                    idx = (idx + 1) % 2;
                } else {
                    break;
                }
            }
        }
        return height;
    }
}

/*
class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        int[] cnt = new int[2];
        for (int i = 1; ; i++) {
            cnt[i % 2] += i;
            if ((cnt[0] > red || cnt[1] > blue) && (cnt[0] > blue || cnt[1] > red)) {
                return i - 1;
            }
        }
    }
}
*/

/*
class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(f(red, blue), f(blue, red));
    }

    private int f(int n, int m) {
        int odd = (int) Math.sqrt(n);
        int even = (int) ((Math.sqrt(m * 4 + 1) - 1) / 2);
        return odd > even ? even * 2 + 1 : odd * 2;
    }
}
*/