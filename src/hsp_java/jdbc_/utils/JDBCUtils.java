package hsp_java.jdbc_.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@SuppressWarnings({"all"})
//这是一个工具类，完成mysql的连接和关闭资源
public class JDBCUtils {
    //定义相关的属性（4个），因为只需要一份，因此，用static
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    //在static代码块中初始化
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("/Users/jijunwei/JavaProjects/src/hsp_java/jdbc/mysql.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
        } catch (IOException e) {
            //在实际开发中，我们可以这样处理
            //将编译异常，转成运行异常，调用者可以选择捕获异常，也可以选择默认处理该异常，比较方便
            throw new RuntimeException(e);
        }
    }

    //连接数据库，返回Connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭相关资源
    public static void close(ResultSet set, Statement statement, Connection connection) {
        //判断是否是null
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
