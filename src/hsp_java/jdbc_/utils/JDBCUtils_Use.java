package hsp_java.jdbc_.utils;

import org.junit.jupiter.api.Test;

import java.sql.*;

@SuppressWarnings({"all"})
//演示使用JDBCUtils工具类，完成dml和dql
public class JDBCUtils_Use {
    @Test
    public void testDML() {
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "update actor set name = ? where id = ?";
        PreparedStatement preparedStatement = null;

        //3.创建PreparedStatement对象
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //给占位符赋值
            preparedStatement.setString(1, "鞠婧祎");
            preparedStatement.setInt(2, 2);
            //执行
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    @Test
    public void testSelect() {
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "select * from actor";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //3.创建PreparedStatement对象
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //执行
            resultSet = preparedStatement.executeQuery();
            //遍历结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); //获取该行的第一列
                String name = resultSet.getString("name"); //获取该行的第二列
                String sex = resultSet.getString("sex"); //获取该行的第三列
                Date date = resultSet.getDate("borndate"); //获取该行的第四列
                String phone = resultSet.getString("phone");
                System.out.println(id + "\t" + name + "\t" + sex + "\t" + date + "\t" + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
    }
}
