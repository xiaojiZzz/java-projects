package hsp_java.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//外部使用反向引用+结巴去重
public class RevReference$ {
    public static void main(String[] args) {

        String content = "我....我要....学学学学....编程....java";
        //1.去掉所有的.
        Pattern pattern = Pattern.compile("\\.");
        Matcher matcher = pattern.matcher(content);
        content = matcher.replaceAll("");

        //2.去掉重复的字
        content = Pattern.compile("(.)\\1+").matcher(content).replaceAll("$1");
        System.out.println(content);
    }
}
