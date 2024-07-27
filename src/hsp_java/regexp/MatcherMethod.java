package hsp_java.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"all"})
//Matcher 类的常用方法
public class MatcherMethod {
    public static void main(String[] args) {
        String content = "hello edu jack hspedu tom hello hspedu smith hello";
//        String regStr = "hello";
        String regStr = "hello.*";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("======================");
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("找到：" + content.substring(matcher.start(), matcher.end()));
        }

        //整体匹配方法，常用于去校验某个字符串是否符合某种规则
        System.out.println("整体匹配 = "+ matcher.matches());

        //完成如果content有hspedu，替换成韩顺平教育
        regStr = "hspedu";
        pattern = Pattern.compile(regStr);
        matcher = pattern.matcher(content);
        //注意：返回的字符串才是替换后的字符串，原来的 content 不变化matcher.matches()
        String newContent = matcher.replaceAll("韩顺平教育");
        System.out.println("新的字符串 = " + newContent);
        System.out.println("旧的字符串 = " + content);
    }
}
