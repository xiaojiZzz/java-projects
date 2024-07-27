package hsp_java.jdbc_.statement_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings({"all"})
//演示statement的注入问题
//此时：name = 1' or
//pwd = or '1' = '1 虽然用户名和密码都不正确，但是登陆成功了
//解决方法：使用PreparedStatement，而不使用Statement
public class Statement_ {
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

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql = "select name, pwd from admin where name = '" + admin_name + "' and pwd = '" + admin_pwd + "'";

        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("恭喜，登陆成功");
        } else {
            System.out.println("对不起，登陆失败");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
