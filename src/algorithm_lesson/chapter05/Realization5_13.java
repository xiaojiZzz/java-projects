package algorithm_lesson.chapter05;

@SuppressWarnings({"all"})
//算法实现题5-13 工作分配问题 compute()用于得出最低的费用；
// p矩阵：工作i分配给第j个人所需的费用；best：最低费用；
// r矩阵：表示任务i由哪个人完成；bestr矩阵：当前任务i最优由哪个人完成
public class Realization5_13 {
//    void backtrack(int t) {
//        if (t > n)
//            compute();
//        else {
//            for (int j = t; j <= n; j++) {
//                swap(r[t], r[j]);
//                backtrack(t+1);
//                swap(r[t], r[j]);
//            }
//        }
//    }
//
//    void compute(void) {
//        for (int i = 1, temp = 0; i <= n; i++) {
//            temp += p[i][r[i]];
//        }
//        if (temp < best) {
//            best = temp;
//            for (int i = 1; i <= n; i++) {
//                bestr[i] = r[i];
//            }
//        }
//    }
}
