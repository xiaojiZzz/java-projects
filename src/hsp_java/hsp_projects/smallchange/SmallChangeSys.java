package hsp_java.hsp_projects.smallchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {
//    面向过程版本
//    化繁为简
//    1.先完成提示菜单，并可以选择菜单，给出对应提示
//    2.完成零钱通明细
//    3.完成收益入账
//    4.完成消费
//    5.退出
//    6.在消费和收益入账时，判断金额是否合理，并给出相应的提示

    public static void main(String[] args) {
//        1.定义相关的变量
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";

//        2.完成零钱通明细
//        思路：（1）可以把收益入账和消费，保存到数组  （2）可以使用数组  （3）简单的话可以使用 String 拼接
        String details = "--------------零钱通明细--------------";

//        3.完成收益入账
//        思路：定义新的变量
        double money = 0;
        double balance = 0;
        Date date = null;  // date 是 java.util.Date 类型，表示日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 可以用于日期格式化

//        4.完成消费
//        定义新的变量，保存消费的出处
        String note = "";

        do {
            System.out.println("\n--------------零钱通菜单--------------");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消费");
            System.out.println("\t\t\t4 退出");

            System.out.print("请选择（1-4）：");
            key = scanner.next();

//            使用 switch 分支控制
            switch (key) {
                case "1":
                    System.out.println(details);
                    break;
                case "2":
                    System.out.println("收益入账金额：");
                    money = scanner.nextDouble();
//                    money 的值应该校验 找出不正确的金额，给出提示即可
                    if (money <= 0) {
                        System.out.println("收益金额需要大于 0");
                        break;
                    }
                    balance += money;
//                    拼接收益入账信息到 detail
                    date = new Date(); // 获取当前的日期
                    details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case "3":
                    System.out.println("消费金额：");
                    money = scanner.nextDouble();
//                    money 的值应该校验
                    if (money <= 0 || money > balance) {
                        System.out.println("您的消费金额应在 0 - " + balance);
                        break;
                    }
                    System.out.println("消费出处");
                    note = scanner.next();
                    balance -= money;
//                    拼接消费信息到 detail
                    date = new Date(); // 获取当前的日期
                    details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case "4":
//                    y/n来判断是否退出
//                    思路：1.定义一个变量 choice 接收用户输入
//                    2.使用 while + break 处理接收的输入
//                    3.退出循环后，再判断 choice 是 y 还是 n
//                    4.建议一段代码完成一个功能
                    String choice = "";
                    while (true) {
                        System.out.println("你确定要退出吗？y/n");
                        choice = scanner.next();
                        if ("y".equals(choice) || "n".equals(choice)) {
                            break;
                        }
                    }
                    if ("y".equals(choice)) {
                        loop = false;
                    }
                    break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        } while (loop);

        System.out.println("-----------退出了零钱通项目-----------");
    }
}
