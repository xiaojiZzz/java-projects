package algorithm_lesson.chapter05;

@SuppressWarnings({"all"})
//算法实现题 最小重量机器设计问题 n：部件数；m：供应商bestx数组：当前最优解；
// x数组：当前解；cc：规定价格；cw：当前重量；bestw：最优重量
// w:重量矩阵；c价格矩阵；cp：当前价格
public class Realization5_3 {
    //bool backtrack(int i) {
    //  if(i > n) { //搜索到底层
    //      for(int j = 1; j <= n; j++) {
    //          bestx[j] = x[j];
    //      }
    //      bestw = cw;
    //      return true;
    //  }
    //  搜索子树
    //  bool found = false;
    //  if(bestw <= cc) {
    //      found = true;
    //  }
    //  for(int j = 1; j <= m; j++) {
    //      x[i] = j;
    //      cw += w[i][j];
    //      cp += c[i][j];
    //      if(cp <= cc && cw < bestw)
    //          if(backtrack(i+1))
    //              found = true;
    //      cw -= w[i][j];
    //      cp -= c[i][j];
    //  }
    //  return found;
    //}
}
