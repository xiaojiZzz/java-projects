package leetcode.lcr;


/**
 * 同leetcode 304.二维区域和检索-矩阵不可变
 */
public class Solution_013 {
    class NumMatrix {
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            int rowLen = matrix.length;
            int colLen = matrix[0].length;
            sums = new int[rowLen + 1][colLen + 1];
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    sums[i + 1][j + 1] = sums[i + 1][j] + sums[i][j + 1] - sums[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 + 1][col2 + 1] - sums[row2 + 1][col1] - sums[row1][col2 + 1] + sums[row1][col1];
        }
    }
}

/*
public class Solution_304 {
    class NumMatrix {
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            int rowLen = matrix.length;
            int colLen = matrix[0].length;
            sums = new int[rowLen][colLen + 1];
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    sums[i][j + 1] = sums[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += sums[i][col2 + 1] - sums[i][col1];
            }
            return sum;
        }
    }
}
*/
