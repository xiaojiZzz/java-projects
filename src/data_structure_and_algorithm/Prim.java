package data_structure_and_algorithm;

import java.util.Arrays;


// 可以用于求解最小生成树，以结点为基础
public class Prim {
    private int n;
    private boolean[] inMST;
    // graph: int[i][j] = weight, i -> j : weight
    // 若 i -> j 不可达，weight为 Integer.MAX_VALUE
    private int[][] graph;
    private int minWeightSum = 0;

    public Prim(int[][] graph) {
        this.graph = graph;
        this.n = graph.length;
        this.inMST = new boolean[n];
    }

    public int getMST() {
        int[] weight = new int[n];
        Arrays.fill(weight, Integer.MAX_VALUE);
        inMST[0] = true;
        // 更新未选中点到点0的最小权重
        for (int i = 1; i < n; i++) {
            weight[i] = graph[i][0];
        }
        // 选中的个数
        int num = 1;
        while (num < n) {
            int minIndex = 0;
            int minDistance = Integer.MAX_VALUE;
            // 获取最短distance
            for (int i = 1; i < n; i++) {
                if (!inMST[i] && minDistance > weight[i]) {
                    minDistance = weight[i];
                    minIndex = i;
                }
            }
            minWeightSum += minDistance;
            inMST[minIndex] = true;
            num++;
            // 更新未选中点到选中点的最小权重
            for (int i = 0; i < n; i++) {
                if (!inMST[i]) {
                    weight[i] = Math.min(weight[i], graph[i][minIndex]);
                }
            }
        }
        return minWeightSum;
    }
}
