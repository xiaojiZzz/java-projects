package algorithm_lesson.chapter05;

@SuppressWarnings({"all"})
//符号三角形问题
public class Triangle {
//n：第一行的个数；half：n*（n+1）/4；count：当前"+"的个数；p数组：符号三角形矩阵；sum：已经找到的符号三角形个数
//    void backtrace(int t) {
//        if ((count > half) || (t * (t - 1) / 2 - count > half))
//            return;
//        if (t > n)
//            sum++;
//        else
//            for (int i = 0; i < 2; i++) {
//                p[1][t] = i; //进入左右儿子数，0或1选择
//                count += i; //’+’计数count
//                for (int j = 2; j <= t; j++) {
//                    p[j][t - j + 1] = p[j - 1][t - j + 1] ^ p[j - 1][t - j + 2]; //倒符号三角形加一边的计算
//                    count += p[j][t - j + 1]; //’+’计算数count
//                }
//                backtrack(t + 1); //进入子树
//                for (int j = 2; j <= t; j++)//回溯或回退，修复计算count
//                    count -= p[j][t - j + 1];
//                count -= i;
//            }
//    }
}
