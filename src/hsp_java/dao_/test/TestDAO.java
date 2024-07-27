package hsp_java.dao_.test;

import hsp_java.dao_.dao.ActorDAO;
import hsp_java.dao_.domain.Actor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {
    @Test
    //测试ActorDAO对actor表的crud操作
    public void testActorDAO() {
        ActorDAO actorDAO = new ActorDAO();
        //1.查询
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("=======查询结果=======");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        //2.查询单行记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 2);
        System.out.println("=====查询单行记录=====");
        System.out.println(actor);

        //3.查询单行单列记录
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 2);
        System.out.println("=====查询单行单列记录=====");
        System.out.println(o);

        //4.dml操作，insert，update，delete
        int update = actorDAO.update("insert into actor values(null, ?, ?, ?, ?)", "张无忌", "男", "2000-10-14", "999");
        System.out.println(update > 0 ? "执行成功" : "没有影响到表");
    }
}
