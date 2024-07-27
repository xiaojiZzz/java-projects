package algorithm_lesson.chapter04;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings({"all"})
//贪心算法求解背包问题
public class Knapsack {
    public static int n = 3; //物品数
    public static int val = 50; //背包容量
    public static int[] w = {20, 30, 10}; //物品重量
    public static int[] v = {60, 120, 50}; //物品价值

    public static void getMaxValue(int n, int val, int[] w, int[] v) {
        //得到价值比 价值比和物品重量
        double[][] goods = new double[n][2];
        for (int i = 0; i < n; i++) {
            double ratio = v[i] / w[i];
            goods[i][0] = ratio;
            goods[i][1] = w[i];
        }
        //对价值比进行排序
        Arrays.sort(goods, new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return (int) (a[0] - b[0]);
            }
        });
        int max = 0;
        //遍历物品，价值比最大的优先加入背包，物品可拆分
        for (int i = n - 1; val > 0 && i >= 0; i--) {
            double w1 = goods[i][1];
            double v1 = goods[i][0] * goods[i][1];
            if (goods[i][1] < val) {    //可以放入背包
                max += v1;
                val -= w1;
            } else {
                //物品放不入背包，对物品进行拆分
                max += ((double) val / w1) * v1;
            }
        }
        System.out.println("最大价值为：" + max);
    }

    public static void main(String[] args) {
        getMaxValue(n, val, w, v);
    }
}
