package hsp_java.jdbc_.transaction_;

import hsp_java.jdbc_.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings({"all"})
//演示在jdbc中如何使用事务
public class Transaction_ {
    @Test
    public void noTransaction() {
        //操作转账业务:没有使用事务
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;

        //3.创建PreparedStatement对象
        try {
            connection = JDBCUtils.getConnection(); //在默认情况下，connection是默认自动提交
            //执行第一条sql
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            int i = 1 / 0; //抛出异常

            //执行第二条sql
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    @Test
    //利用事务来解决问题
    public void useTransaction() {
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;

        //3.创建PreparedStatement对象
        try {
            connection = JDBCUtils.getConnection(); //在默认情况下，connection是默认自动提交
            //将 connection 设置为不自动提交
            connection.setAutoCommit(false); //相当于开启了事务
            //执行第一条sql
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            int i = 1 / 0; //抛出异常

            //执行第二条sql
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();

            //提交事务
            connection.commit();

        } catch (SQLException e) {
            //这里我们可以进行回滚，即撤销执行的sql，默认回滚到事务开始的状态
            System.out.println("执行发生了异常，撤销执行的sql");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //关闭资源
            System.out.println("关闭资源");
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }
}
