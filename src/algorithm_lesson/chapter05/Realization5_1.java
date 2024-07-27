package algorithm_lesson.chapter05;

@SuppressWarnings({"all"})
//算法实现题5-1 子集和问题 n：正整数个数；bestx数组：当前最优解；
// x数组：当前解；cw：当前和；bestw：最优和；c：子集和目标值；w数组：正整数集合
public class Realization5_1 {
    //bool backtrack(int i) {
    //  if(i > n) { //搜索到底层
    //      for(int j = 1; j <= n; j++) {
    //          bestx[j] = x[j];
    //      }
    //      bestw = cw;
    //      if(bestw == c)
    //          return true;
    //      else
    //          return false;
    //  }
    //  搜索子树
    //  r -= w[i]; //r为总和
    //  if(cw + w[i] <= c) { //搜索左子树
    //      x[i] = 1;
    //      cw += w[i];
    //      if(backtrace(i+1))
    //          return true;
    //      cw -= w[i];
    //  }
    //  if(cw + r > bestw) { //搜索右子树
    //      x[i] = 0;
    //      if(backtrace(i+1))
    //          return true;
    //  }
    //  r += w[i];
    //  return false;
    //}
}
