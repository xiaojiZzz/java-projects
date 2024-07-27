package hsp_java.jdbc_.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

@SuppressWarnings({"all"})
//测试druid的使用，阿里数据库连接池产品
public class Druid_ {
    @Test
    public void testDruid() throws Exception{
        //1.加入Druid的jar包
        //2.加入配置文件 druid.properties，将该文件拷贝到项目的指定目录
        //3.创建Properties对象，读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/jijunwei/JavaProjects/src/hsp_java/jdbc/datasource/druid.properties"));

        //4.创建一个指定参数的数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Druid连接池耗时：" + (end - start));
    }
}
