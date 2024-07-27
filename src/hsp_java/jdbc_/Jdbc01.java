package hsp_java.jdbc_;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@SuppressWarnings({"all"})
public class Jdbc01 {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        Driver driver = new Driver();
        //2.得到连接
        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        //将用户名和密码放入到Properties对象中
        Properties properties = new Properties();
        properties.setProperty("user", "root"); //用户
        properties.setProperty("password", "jjw000000"); //密码

        Connection connect = driver.connect(url, properties);
        //3.执行sql
        String sql = "update actor set name='xxx' where id=1";
        //statement 用于执行静态sql语句并返回其生成的结果的对象
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql); //如果是dml语句，返回的就是影响的行数

        System.out.println(rows > 0 ? "成功" : "失败");
        //4.关闭连接
        statement.close();
        connect.close();
    }
}
