package data_structure_and_algorithm;

@SuppressWarnings({"all"})
//可用于求解最短路径问题 可解决带负权值的图，但不能解决带负权回路的图
public class Floyd {
    private int n;
    //graph: int[i][j] = weight, i -> j : weight
    private int[][] graph;
    //中转点
    private int[][] path;

    public Floyd(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        path = new int[n][n];
    }

    public void floyd() {
        //三层循环：枚举中转点 - 枚举起点 - 枚举终点
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }
}
