package hsp_java.jdbc_.batch_;

import hsp_java.jdbc_.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

@SuppressWarnings({"all"})
//演示java的批处理
public class Batch_ {
    //传统的方法，添加5000条数据到admin2
    @Test
    public void noBatch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("开始执行");
        long start = System.currentTimeMillis(); //开始时间

        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setString(2, "666");
            preparedStatement.executeUpdate();
        }

        long end = System.currentTimeMillis(); //结束时间
        System.out.println("传统的方式耗时时间为：" + (end - start));

        JDBCUtils.close(null, preparedStatement, connection);
    }

    //使用批量方式添加数据
    @Test
    public void batch() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("开始执行");
        long start = System.currentTimeMillis(); //开始时间

        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setString(2, "666");
            //将sql语句加入到批处理包中
            preparedStatement.addBatch();
            //当有1000条记录时，再批量处理
            if ((i+1) % 1000 == 0) {
                preparedStatement.executeBatch();
                //清空batch
                preparedStatement.clearBatch();
            }
        }

        long end = System.currentTimeMillis(); //结束时间
        System.out.println("批量的方式耗时时间为：" + (end - start));

        JDBCUtils.close(null, preparedStatement, connection);
    }
}
