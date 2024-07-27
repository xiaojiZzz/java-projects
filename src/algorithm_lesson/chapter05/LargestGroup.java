package algorithm_lesson.chapter05;

@SuppressWarnings({"all"})
//最大团问题 a矩阵：图G的邻接矩阵；n：图的顶点数；x矩阵：当前解；
// bestx：当前最优解；cn：当前顶点数；bestn：当前最大顶点数
public class LargestGroup {
//    void Clique::Backtrack(int i) {// 计算最大团
//        if (i > n) {// 到达叶结点
//            for (int j = 1; j <= n; j++)
//                bestx[j] = x[j];
//            bestn = cn;
//            return;
//        }
//        // 检查顶点 i 与当前团的连接
//        int OK = 1;
//        for (int j = 1; j < i; j++)
//            if (x[j] && a[i][j] == 0) {
//                // i与j不相连
//                OK = 0;
//                break;
//            }
//        if (OK) {// 进入左子树
//            x[i] = 1;  cn++;
//            Backtrack(i+1);
//            x[i] = 0;  cn--;
//        }
//        if (cn + n - i > bestn) {// 进入右子树
//            x[i] = 0;
//            Backtrack(i+1);
//        }
//    }
}
