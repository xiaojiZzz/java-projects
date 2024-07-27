package hsp_java.regexp;

//演示String类中使用正则表达式
public class StringReg {
    public static void main(String[] args) {

        //要求验证是不是小数或者整数
        String regStr = "^[-+]?([1-9]\\d*|0)(\\.\\d+)?$";
        String content = "Java平台由Java虚拟机(Java Virtual Machine)和Java 应用编程接口" +
                "(Application Programming Interface、简称API)构成。Java 应用编程接口为Java应用提供了一个独立于操作系统" +
                "的标准接口，可分为基本部分和扩展部分。在硬件或操作系统平台上安装一个Java平台之后，Java应用程序就可运行。" +
                "Java平台已经嵌入了几乎所有的操作系统。这样Java程序可以只编译一次，就可以在各种系统中运行。Java应用编程接" +
                "口已经从1.1x版发展到1.2版。常用的Java平台基于Java1.8，最近版本为Java19。";

        //使用正则表达式，将 Java 和 Java应用编程 替换成JAVA
        content = content.replaceAll("Java19|Java1\\.8", "JAVA");
        System.out.println(content);

        //要求验证一个手机号，要求必须以138或139开头
//        content = "13814703992";
        content = "13862748704";
        if (content.matches("1(38|39)\\d{8}")) {
            System.out.println("验证成功");
        } else {
            System.out.println("验证失败");
        }

        //要求按照 # 或者 - 或者 ~ 或者 数字 来分割
        System.out.println("==========================");
        content = "hello#abc-jack12smith~北京";
        String[] split = content.split("#|-|~|\\d+");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
