package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//流水作业调度问题 c++程序
public class JobScheduleProblem {
//    #define N 100
//    struct node {
//        int time;//执行时间
//        int index;//作业序号
//        bool group;//1代表第一个机器，0代表第二个机器
//    };
//
//    bool cmp(node a,node b)
//    {//升序排序
//        return a.time<b.time;
//    }
//    int main()
//    {
//        int i,j,k,n;
//        int a[N]={0},b[N]={0};
//        int best[ N];//最优调度序列
//        node c[N];
//        scanf("%d",&n);
//        for(i=0;i<n;i++) {
//            scanf("%d%d",&a[i],&b[i]);
//        }
//        for(i=0;i<n;i++) {  //n个作业中，每个作业的最小加工时间
//            c[i].time=a[i]>b[i]?b[i]:a[i];
//            c[i].index=i;
//            c[i].group=a[i]<=b[i];
//
//        }
//        sort(c,c+n,cmp);//按照c[]中作业时间增序排序
//        j=0,k=n-1;
//        for(i=0;i<n;i++) {
//            if(c[i].group) { //第一组，从i=0开始放入到best[]中
//                best[j++]=c[i].index;
//            }
//            else {
//                best[k--]=c[i].index;
//            }
//        }
//        j=a[best[0]];//最优调度序列下的消耗总时间
//        k=j+b[best[0]];
//        for(i=1;i<n;i++) {
//            j+=a[best[i]];
//            k=j<k?(k+b[best[i]]):j+b[best[i]];//消耗总时间的最大值
//        }
//        printf("%d\n",k);
//        for(i=0;i<n;i++) {
//            printf("%d ",best[i]+1);
//        }
//        printf("\n");
//        return 0;
//    }
}
