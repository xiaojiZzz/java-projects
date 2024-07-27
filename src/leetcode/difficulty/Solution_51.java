package leetcode.difficulty;

import java.util.*;


/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 */
public class Solution_51 {
    private List<List<String>> lists = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] x = new int[n];
        backtrack(0, n, x);
        return lists;
    }

    public void backtrack(int i, int n, int[] x) {
        if (i == n) {
            List<String> list = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                StringBuffer s = new StringBuffer();
                for (int t = 0; t < n; t++) {
                    if (t == x[k]) {
                        s.append("Q");
                    } else {
                        s.append(".");
                    }
                }
                String str = s.toString();
                list.add(str);
            }
            lists.add(list);
        } else {
            for (int j = 0; j < n; j++) {
                x[i] = j;
                if (place(i, x)) {
                    backtrack(i + 1, n, x);
                }
            }
        }
    }

    public boolean place(int i, int[] x) {
        for (int j = 0; j < i; j++) {
            if ((Math.abs(j - i) == Math.abs(x[i] - x[j])) || x[i] == x[j]) {
                return false;
            }
        }
        return true;
    }
}

/*class Solution {
    public List<List<String>> solveNQueens(int n) {
        //定义一个返回结果的集合
        List<List<String>> res = new ArrayList<>();
        //定义一个存储皇后的集合
        int[] queens = new int[n];
        //填充数组queens[]中的每个元素都是-1
        //queens={-1,-1,-1...-1}
        Arrays.fill(queens, -1);
        //定义一个变量，来记录当前元素所在的列，并将他所在的列标记为不可放元素
        Set<Integer> columns = new HashSet<>();
        //定义一个变量，来记录当前元素所在的左对角线，并将他所在的左对角线标记为不可放元素
        Set<Integer> diagonals1 = new HashSet<>();
        //定义一个变量，来纪律当前元素所在的右对角线，并将他所在的右对角线标记为不可放元素
        Set<Integer> diagonals2 = new HashSet<>();
        //深度优先搜索方法
        dfs(res, queens, n, 0, columns, diagonals1, diagonals2);
        return res;
    }

    public void dfs(List<List<String>> res, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        //如果当前遍历到最后一行，就说明存在一个解法
        //所以将皇后的位置，存放入结果中
        if (row == n) {
            //用来将当前的N行N列中的元素所在的位置结果，转换格式
            List<String> board = generateBoard(queens, n);
            //将符合条件的结果添加进返回结果集中
            res.add(board);

        } else {
            //遍历所有行
            for (int i = 0; i < n; i++) {
                //用来标记，当前行元素所在的列，都不可放元素
                if (columns.contains(i)) {
                    continue;
                }
                //去除左对角线上的所有元素
                //row 表示行，i表示列
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                //去除右对角线上的元素
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                //经过上面的三次排除，就可以找到元素在当前行的哪一列的位置。
                //选第一行的第几列，也可以叫单元格所在的位置
                queens[row] = i;
                //把选中的单元格加入到，去除列的集合中
                //用来给下一行的元素所在的列作为排除条件判断
                columns.add(i);
                //把选中的单元格加入到，去除左对角线的集合中
                diagonals1.add(diagonal1);
                //把选中的单元格加入到，去除右对角线的集合中
                diagonals2.add(diagonal2);
                //递归遍历下一行，
                dfs(res, queens, n, row + 1, columns, diagonals1, diagonals2);
                //剪枝操作
                queens[row] = -1;
                //将当前列和左对角线和右对角线的元素都删除，避免重复遍历
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }

    }

    //转换格式
    public List<String> generateBoard(int[] queens, int n) {
        //定义一个结果集，用于返回结果
        List<String> board = new ArrayList<>();
        //遍历所有行
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            //将当前行所在的列的，位置置为Q
            row[queens[i]] = 'Q';
            //将当前结果添加进结果集中
            board.add(new String(row));
        }
        return board;
    }
}*/


