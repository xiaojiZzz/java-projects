package algorithm_lesson.chapter05;

@SuppressWarnings({"all"})
//批处理作业调度
//x数组：当前作业调度；bestx数组：当前最优作业调度；bestf：当前最优值；f：完成时间和；f1：机器1完成处理时间；n：作业数；
// M数组：各作业所需的处理时间；f2数组：机器2完成处理时间
public class FlowShop {
//    void backtrack(int i) {
//        if (i > n) {
//            for (int j = 1; j <= n; j++)
//                bestx[j] = x[j];
//            bestf = f;
//        }
//        else
//            for (int j = i; j <= n; j++) {
//                f1 += M[x[j]][1];
//                f2[i] = ((f2[i-1]>f1) ? f2[i-1] : f1) + M[x[j]][2];
//                f += f2[i];
//                if (f < bestf) {
//                    Swap(x[i], x[j]);
//                    Backtrack(i+1);
//                    Swap(x[i], x[j]);
//                }
//                f1 -= M[x[j]][1];
//                f -= f2[i];
//            }
//    }
}
