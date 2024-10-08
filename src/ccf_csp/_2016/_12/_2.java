package ccf_csp._2016._12;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double t = in.nextDouble();
        in.close();

        // 从高到低判断税后工资是哪一档，注意t一直在变化
        if (t > 61005) {// 当税前工资等于3500+80000=83500，计算可得税后工资为61005，即为此档边界
            t = (t - 61005) / 0.55 + 83500;
        } else if (t > 44755) {// 当税前工资等于3500+55000=58500，计算可得税后工资为44755，即为此档边界
            t = (t - 44755) / 0.65 + 58500;
        } else if (t > 30755) {// 当税前工资等于3500+35000=38500，计算可得税后工资为30755，即为此档边界
            t = (t - 30755) / 0.7 + 38500;
        } else if (t > 11255) {// 当税前工资等于3500+9000=12500，计算可得税后工资为11255，即为此档边界
            t = (t - 11255) / 0.75 + 12500;
        } else if (t > 7655) {// 当税前工资等于3500+4500=8000，计算可得税后工资为7655，即为此档边界
            t = (t - 7655) / 0.8 + 8000;
        } else if (t > 4955) {// 当税前工资等于3500+1500=5000，计算可得税后工资为4955，即为此档边界
            t = (t - 4955) / 0.9 + 5000;
        } else if (t > 3500) {// 当税前工资等于3500，计算可得税后工资为3500，即为此档边界
            t = (t - 3500) / 0.97 + 3500;
        }
        System.out.println((int) t);// 税前工资未达到3500则没有税
    }
}
