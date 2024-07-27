package hsp_java.jdbc_.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

@SuppressWarnings({"all"})
//演示c3p0连接池的使用
public class C3P0_ {

    //方式1：相关的参数，在程序中指定 user, url, password等
    @Test
    public void testC3P0_01() throws Exception {
        //1.创建一个数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //2.通过配置文件mysql.properties获取相关连接的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/jijunwei/JavaProjects/src/hsp_java/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //给数据源 comboPooledDataSource 相关信息
        //注意：连接管理是由 comboPooledDataSource 来管理的
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        //设置最大连接数
        comboPooledDataSource.setMaxPoolSize(50);

        //测试连接池的效率，5000次操作
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Connection connection = comboPooledDataSource.getConnection();//这个方法是从 DataSource 接口实现的
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0 500000次连接mysql耗时：" + (end - start));
    }

    //方式2：使用配置文件模版来完成
    @Test
    public void testC3P0_02() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("jjw_c3p0");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Connection connection = comboPooledDataSource.getConnection(); //这个方法是从 DataSource 接口实现的
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0 500000次连接mysql耗时：" + (end - start));
    }
}
