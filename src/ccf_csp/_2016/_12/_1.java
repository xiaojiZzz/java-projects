package ccf_csp._2016._12;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        int mid = n / 2;
        for (int i = 0; i < mid; i++) {
            if (nums[i] != nums[mid]) {
                left++;
            }
        }
        for (int i = mid + 1; i < n; i++) {
            if (nums[i] != nums[mid]) {
                right++;
            }
        }
        if (left == right) {
            System.out.println(nums[mid]);
        } else {
            System.out.println(-1);
        }
    }
}

/*
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner console=new Scanner(System.in);
        int n,i,j;
        n=console.nextInt();
        int[] a=new int[n];
        for(i=0;i<n;i++)
            a[i]=console.nextInt();
        Arrays.sort(a);//排序
        int mid=n>>1;//排序后，如果存在中间数，必定在中间
        for(i=mid;i>=0&&a[i]==a[mid];i--);  //除去左边与a[mid]相等的数
        for(j=mid;j<n&&a[j]==a[mid];j++);  //除去右边与a[mid]相等的数
        int ans;
        if(i-0+1==n-j)
            ans=a[mid];
        else
            ans=-1;
        System.out.println(ans);
        console.close();
    }
}
*/
