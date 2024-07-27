package hsp_java.hsp_projects.mhl.service;

import hsp_java.hsp_projects.mhl.dao.MenuDAO;
import hsp_java.hsp_projects.mhl.domain.Menu;

import java.util.List;

//该类完成对menu表的各种操作（通过调用MenuDAO对象完成）
public class MenuService {

    //定义一个MenuDAO属性
    private MenuDAO menuDAO = new MenuDAO();

    //返回所有的菜品给主界面使用
    public List<Menu> list() {
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }

    //根据id，返回Menu对象
    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}
