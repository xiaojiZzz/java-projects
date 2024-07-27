package hsp_java.jdbc_;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SuppressWarnings({"all"})
//java连接mysql的5种方式
public class JdbcConnect {

    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://localhost:3306/db1";
        Properties properties = new Properties();
        properties.setProperty("user", "root"); //用户
        properties.setProperty("password", "jjw000000"); //密码

        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    @Test
    public void connect02() throws Exception {
        //使用反射加载Driver类，动态加载，更加的灵活，减少依赖性
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.getConstructor().newInstance();

        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        Properties properties = new Properties();
        properties.setProperty("user", "root"); //用户
        properties.setProperty("password", "jjw000000"); //密码

        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    @Test
    public void connect03() throws Exception {
        //使用DriverManager替代Driver进行统一管理
        //使用反射加载Driver
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.getConstructor().newInstance();

        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        String user = "root";
        String password = "jjw000000";

        DriverManager.registerDriver(driver); //注册Driver驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void connect04() throws Exception {
        //使用Class.forName自动完成注册驱动，简化代码
        Class.forName("com.mysql.cj.jdbc.Driver"); //在加载Driver类时，完成了DriverManager对于Driver的注册

        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        String user = "root";
        String password = "jjw000000";

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void connect05() throws Exception {
        //在方式4的基础上，增加配置文件，让连接mysql更加灵活
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/jijunwei/JavaProjects/src/hsp_java/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
