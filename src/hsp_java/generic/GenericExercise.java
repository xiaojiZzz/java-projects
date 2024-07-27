package hsp_java.generic;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 定义Employee类
 * 1) 该类包含：private成员变量name,sal,birthday，其中birthday 为 MyDate 类的对象;
 * 2) 为每一个属性定义 getter, setter 方法;
 * 3) 重写 toString 方法輸出 name, sal, birthday
 * 4) MyDate类包含：private成员变量month, day, year：并为每一个属性定义 getter, setter 方法;
 * 5）创建该类的3个对象，并把这些对象放入 ArrayList 集合中 (ArrayList 需使用泛型来定义），
 * 对集合中的元素进行排序，并遍历输出：
 * 排序方式：调用ArrayList 的 sort 方法，传入 Comparator对象 [使用泛型]，先按照
 * name排序，如果name相同，则按生日日期的先后排序。【即：定制排序】
 */
@SuppressWarnings({"all"})
public class GenericExercise {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("tom", 20000, new MyDate(2000, 11, 11)));
        employees.add(new Employee("jack", 12000, new MyDate(2001, 12, 12)));
        employees.add(new Employee("milk", 50000, new MyDate(1999, 10, 10)));

        // 进行排序
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                if (!(employee1 instanceof Employee && employee2 instanceof Employee)) {
                    System.out.println("类型不正确。。。");
                    return 0;
                }
                // 比较 name
                int i = employee1.getName().compareTo(employee2.getName());
                if (i != 0) {
                    return i;
                }
                return employee1.getBirthday().compareTo(employee2.getBirthday());
            }
        });
        System.out.println(employees);
    }
}
