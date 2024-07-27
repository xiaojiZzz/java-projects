package hsp_java.jdbc_.preparedstatement_;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings({"all"})
//演示PreparedStatement的使用
public class PreparedStatement_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //让用户输入管理员名和密码
        System.out.print("请输入管理员的名字：");
        String admin_name = scanner.nextLine(); //scanner.next()方法，当接收到空格或者'就表示结束，而nextLine则是碰到回车才结束
        System.out.print("请输入管理员密码：");
        String admin_pwd = scanner.nextLine();

        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/jijunwei/JavaProjects/src/hsp_java/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        String sql = "select name, pwd from admin where name = ? and pwd = ?"; //sql语句的? 相当于占位符
        Connection connection = DriverManager.getConnection(url, user, password);
        //preparedStatement 对象实现了 PreparedStatement 接口的实现类的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给?赋值
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        //执行 select 语句使用 executeQuery()
        //如果执行 dml语句（update，insert，delete）使用 executeUpdate()
        ResultSet resultSet = preparedStatement.executeQuery(); //()里不要加sql，不然会把?加入sql语句中，产生SQLSyntaxErrorException
        if (resultSet.next()) {
            System.out.println("恭喜，登陆成功");
        } else {
            System.out.println("对不起，登陆失败");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
