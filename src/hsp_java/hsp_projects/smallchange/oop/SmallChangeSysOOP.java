package hsp_java.hsp_projects.smallchange.oop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSysOOP { // 面向对象版本
//    1.定义相关的变量
    boolean loop = true;
    Scanner scanner = new Scanner(System.in);
    String key = "";

//    2.完成零钱通明细
//    思路：（1）可以把收益入账和消费，保存到数组  （2）可以使用数组  （3）简单的话可以使用 String 拼接
    String details = "--------------零钱通明细--------------";

//    3.完成收益入账
//    思路：定义新的变量
    double money = 0;
    double balance = 0;
    Date date = null;  // date 是 java.util.Date 类型，表示日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 可以用于日期格式化

//    4.完成消费
//    定义新的变量，保存消费的出处
    String note = "";

//    各个功能对应一个方法
//    先完成显示菜单，并可以选择
    public void mainMenu() {
        do {
            System.out.println("\n--------------零钱通Menu--------------");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消费");
            System.out.println("\t\t\t4 退出");

            System.out.print("请选择（1-4）：");
            key = scanner.next();

//            使用 switch 分支控制
            switch (key) {
                case "1":
                    this.detail();
                    break;
                case "2":
                    this.income();
                    break;
                case "3":
                    this.pay();
                    break;
                case "4":
                    this.exit();
                    break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        } while (loop);

        System.out.println("-----------退出了零钱通项目-----------");
    }
//    完成零钱通明细
    public void detail() {
        System.out.println(details);
    }
//    完成收益入账
    public void income() {
        System.out.println("收益入账金额：");
        money = scanner.nextDouble();
//        money 的值应该校验 找出不正确的金额，给出提示，就直接 return
        if (money <= 0) {
            System.out.println("收益金额需要大于 0");
            return; // 退出方法，不再执行后面的代码
        }
//        找出正确金额的条件
        balance += money;
//        拼接收益入账信息到 detail
        date = new Date(); // 获取当前的日期
        details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
    }
//    消费
    public void pay() {
        System.out.println("消费金额：");
        money = scanner.nextDouble();
//        money 的值应该校验
        if (money < 0 || money > balance) {
            System.out.println("您的消费金额应在 0 - " + balance);
            return;
        }
        System.out.println("消费出处");
        note = scanner.next();
        balance -= money;
//        拼接消费信息到 detail
        date = new Date(); // 获取当前的日期
        details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;
    }
//    退出
    public void exit() {
//        y/n来判断是否退出
//        思路：1.定义一个变量 choice 接收用户输入
//        2.使用 while + break 处理接收的输入
//        3.退出循环后，再判断 choice 是 y 还是 n
//        4.建议一段代码完成一个功能
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
    }
}
