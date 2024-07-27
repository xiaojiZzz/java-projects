package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//算法实现题3-17 字符串比较问题 c++
public class Realization3_17 {
//    #define mins(a,b,c) min(min(a,b),c)
//    string A,B;
//    int dp[1001][1001];
//    int dpComp(int k)
//    {
//        for(int i=1;i<1001;i++)//预处理
//        {
//            dp[0][i]=k*i;
//            dp[i][0]=k*i;
//        }
//        dp[0][0]=0;
//        int len1=A.length(),len2=B.length();
//        for(int i=1;i<=len1;i++)
//        {
//            for(int j=1;j<=len2;j++)
//            {
//                dp[i][j]=mins(dp[i-1][j]+k,dp[i][j-1]+k,dp[i-1][j-1]+abs(A[i-1]-B[j-1]));
//            }
//        }
//        return dp[len1][len2];
//    }
//    int main()
//    {
//        int k;
//        cin>>A>>B>>k;
//        int res=dpComp(k);
//        cout<<res<<endl;
//        return 0;
//    }
}
