package hsp_java.collection_.map_;

import java.util.*;

@SuppressWarnings({"all"})
public class Map_for {
    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("邓超", "孙俪");
        map.put("王宝强", "马蓉");
        map.put("宋喆", "马蓉");
        map.put("刘令博", null);
        map.put(null, "刘亦菲");
        map.put("鹿晗", "关晓彤");

        // 第一组：先取出所有的key，通过key取出对应的value
        Set keySet = map.keySet();
        // (1) 增强 for
        System.out.println("-------第一种方式--------");
        for (Object key : keySet) {
            System.out.println(key + "-" + map.get(key));
        }
        //(2) 迭代器
        System.out.println("-------第二种方式--------");
        Iterator iterator1 = keySet.iterator();
        while (iterator1.hasNext()) {
            Object key = iterator1.next();
            System.out.println(key + "-" + map.get(key));
        }

        // 第二组：把所有的values取出
        Collection values = map.values();
        // (1) 增强for
        System.out.println("-----取出所有的value(增强for)-----");
        for (Object value : values) {
            System.out.println(value);
        }
        // (2) 迭代器
        System.out.println("-----取出所有的value(迭代器)-----");
        Iterator iterator2 = values.iterator();
        while (iterator2.hasNext()) {
            Object value = iterator2.next();
            System.out.println(value);
        }

        // 第三组：通过EntrySet 来获取k-vfff
        Set entrySet = map.entrySet();
        // (1) 增强 for
        System.out.println("----使用 EntrySet 的 for----");
        for (Object entry : entrySet) {
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }
        // (2) 迭代器
        System.out.println("----使用 EntrySet 的迭代器----");
        Iterator iterator3 = entrySet.iterator();
        while (iterator3.hasNext()) {
            Object entry = iterator3.next();
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }
    }
}
