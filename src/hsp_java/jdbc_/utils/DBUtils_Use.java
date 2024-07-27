package hsp_java.jdbc_.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

@SuppressWarnings({"all"})
//使用apache-DBUtils 工具类 + Druid 完成对表的crud操作
public class DBUtils_Use {

    @Test
    public void testQueryMany() throws Exception { //返回结果是多行的情况

        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用 DBUtils 类和接口，先引入DBUtils 相关的jar文件
        //3.创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4.就可以执行相关的方法，返回ArrayList结果集
        String sql = "select * from actor where id >= ?";
        //解读：query方法就是执行sql语句，得到resultset --封装--> ArrayList 集合中,返回集合
        //new BeanListHandler<>(Actor.class)：在将resultset->Actor对象->封装到ArrayList中,底层会使用反射机制，
        //去获取Actor类的属性，然后进行封装
        //最后的 1 是给 sql 语句里的 ? 赋值
        //底层得到的resultset，会在query结束时关闭，PreparedStatement也会关闭
        List<Actor> list = queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), 1);

        System.out.print("输出集合的信息");
        for (Actor actor : list) {
            System.out.print(actor);
        }

        //关闭连接
        JDBCUtilsByDruid.close(null, null, connection);
    }

    //返回的结果是单行记录（单个对象）
    @Test
    public void testQuerySingle() throws Exception {
        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用 DBUtils 类和接口，先引入DBUtils 相关的jar文件
        //3.创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4.就可以执行相关的方法，返回单个对象
        String sql = "select * from actor where id = ?";

        Actor actor = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 2);
        System.out.println(actor);

        //释放资源
        JDBCUtilsByDruid.close(null, null, connection);
    }

    //完成查询结果是单行单列的情况-返回的是Object对象
    @Test
    public void testScalar() throws Exception {
        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用 DBUtils 类和接口，先引入DBUtils 相关的jar文件
        //3.创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4.就可以执行相关的方法，返回单行单列的情况
        String sql = "select name from actor where id = ?";

        Object obj = queryRunner.query(connection, sql, new ScalarHandler<>(), 2);
        System.out.println(obj);

        //释放资源
        JDBCUtilsByDruid.close(null, null, connection);
    }

    //完成dml语句
    @Test
    public void testDML() throws Exception {
        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用 DBUtils 类和接口，先引入DBUtils 相关的jar文件
        //3.创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4.就可以执行相关的方法，完成update, insert, delete
//        String sql = "update actor set name = ? where id = ?";
//        String sql = "insert into actor values(?, ?, ?, ?, ?)";
        String sql = "delete from actor where id = ?";

        //执行dml操作，用queryRunner.update()方法（delete、insert也是）
        //返回值是受影响的行数
        int affectedRow = queryRunner.update(connection, sql, 1);
        System.out.println(affectedRow > 0 ? "执行成功" : "执行没有受到影响");

        //释放资源
        JDBCUtilsByDruid.close(null, null, connection);
    }
}
