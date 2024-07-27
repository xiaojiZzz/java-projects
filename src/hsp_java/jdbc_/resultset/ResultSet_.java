package hsp_java.jdbc_.resultset;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

@SuppressWarnings({"all"})
//演示select语句返回ResultSet，并取出结果
public class ResultSet_ {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/jijunwei/JavaProjects/src/hsp_java/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql = "select id, name, sex, borndate from actor";
        //执行给定的sql语句，该语句返回单个ResultSet对象
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt(1); //获取该行的第一列
            String name = resultSet.getString(2); //获取该行的第二列
            String sex = resultSet.getString(3); //获取该行的第三列
            Date date = resultSet.getDate(4); //获取该行的第四列
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
