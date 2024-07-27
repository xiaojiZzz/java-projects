package hsp_java.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//演示分组捕获反向引用
public class RevReference {
    public static void main(String[] args) {

        String content = "hello33333 jack14 tom11 jack22 yyy12345 xxx4554";
        //要匹配两个连续的相同数字：(\\d)\\1
//        String regStr = "(\\d)\\1";
        //要匹配五个连续的相同数字：(\\d)\\1{4}
//        String regStr = "(\\d)\\1{4}";
        //要匹配个位与千位相同，十位与百位相同的数字：(\\d)(\\d)\\2\\1
        String regStr = "(\\d)(\\d)\\2\\1";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到：" + matcher.group(0));
        }
    }
}
