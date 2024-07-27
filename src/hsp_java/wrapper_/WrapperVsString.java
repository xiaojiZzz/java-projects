package hsp_java.wrapper_;

public class WrapperVsString {
    public static void main(String[] args) {
        // 包装类（Integer）-> String
        Integer i = 100;
        // 方式1
        String str1 = i + "";
        // 方式2
        String str2 = i.toString();
        // 方式3
        String str3 = String.valueOf(i);

        // String -> 包装类（Integer）
        String str = "1243";
        // 方式1
        int i1 = Integer.parseInt(str);
        // 方式2
        int i2 = new Integer(str); // 已经废弃
        // 方式3
        int i3 = Integer.valueOf(str); // 有拆箱过程

        System.out.println("完成转换");
        Integer m = 1;
        Integer n = 1;
        System.out.println(m == n); // true -128~127 之间不会new一个新的Integer对象
    }
}
