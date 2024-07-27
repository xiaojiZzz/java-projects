package algorithm_lesson.chapter05;

@SuppressWarnings({"all"})
//装载问题
public class LoadingProblem {
    /*
    n：集装箱个数；w数组：集装箱重量；cw：当前载重量；bestw：最优载重量；
    c：第一艘轮船的载重量；bestx数组：当前最优解；x数组：当前解
    void backtrack(int i) {
      if(i > n) { //搜索到底层
          if(cw > bestw) {
              for(int j = 1; j <= n; j++) {
                  bestx[j] = x[j];
              }
              bestw = cw;
          }
          return;
      }
      搜索子树
      r -= w[i]; //r为总的重量
      if(cw + w[i] <= c) { //搜索左子树
          x[i] = 1;
          cw += w[i];
          backtrace(i+1);
          cw -= w[i];
      }
      if(cw + r > bestw) { //搜索右子树
          x[i] = 0;
          backtrace(i+1);
      }
      r += w[i];
    }
    */
}
