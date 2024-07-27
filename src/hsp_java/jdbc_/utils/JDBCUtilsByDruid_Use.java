package hsp_java.jdbc_.utils;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings({"all"})
//JDBCUtilsByDruid工具类的使用
public class JDBCUtilsByDruid_Use {
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
            connection = JDBCUtilsByDruid.getConnection();
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
            JDBCUtilsByDruid.close(resultSet, preparedStatement, connection);
        }
    }

    //使用土办法来解决ResultSet关闭后无法使用的问题 =封装=> ArrayList
    @Test
    public ArrayList<Actor> testSelectToArrayList() {
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "select * from actor";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Actor> list = new ArrayList<>();

        //3.创建PreparedStatement对象
        try {
            connection = JDBCUtilsByDruid.getConnection();
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
                //把得到的resultset的记录，封装到Actor对象中，并将其放到list集合中
                list.add(new Actor(id, name, sex, date, phone));
            }

            System.out.println("list集合中的数据：" + list);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtilsByDruid.close(resultSet, preparedStatement, connection);
        }
        //因为ArrayList和Connection没有任何关系，Connection关闭后，ArrayList中保存了Connection中的信息，可以返回使用
        return list; //@Test如果有返回值，就不会输出内容
    }
}
