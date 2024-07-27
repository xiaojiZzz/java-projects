package hsp_java.string_;

public class StringVsStringBuffer {
    public static void main(String[] args) {
        // String -> StringBuffer
        String str = "hello";
        // 方式1 使用构造器
        StringBuffer stringBuffer = new StringBuffer(str);
        // 方式2 使用append
        StringBuffer stringBuffer1 = new StringBuffer();
        stringBuffer1.append(str);

        // StringBuffer -> String
        StringBuffer stringBuffer2 = new StringBuffer("happy");
        // 方式1 使用toString方法
        String str1 = stringBuffer2.toString();
        // 方式2 使用构造器
        String str2 = new String(stringBuffer2);

    }
}
