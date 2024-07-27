package leetcode.medium;


/**
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 */
public class Solution_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int r = mid / n, c = mid % n;
            if (matrix[r][c] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == 0 && matrix[0][0] != target || left == m * n && matrix[m - 1][n - 1] != target) {
            return false;
        }
        return matrix[left / n][left % n] == target;
    }
}

/*
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
*/

/*
class Solution {
    int m, n;
    public boolean searchMatrix(int[][] mat, int t) {
        m = mat.length; n = mat[0].length;
        int x = 0, y = n - 1;
        while (check(x, y) && mat[x][y] != t) {
            if (mat[x][y] > t) {
                y--;
            } else {
                x++;
            }
        }
        return check(x, y) && mat[x][y] == t;
    }
    boolean check(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
*/
