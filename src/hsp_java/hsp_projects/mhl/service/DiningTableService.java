package hsp_java.hsp_projects.mhl.service;

import hsp_java.hsp_projects.mhl.dao.DiningTableDAO;
import hsp_java.hsp_projects.mhl.domain.DiningTable;

import java.util.List;

//该类完成对diningTable表的各种操作（通过调用DiningTableDAO对象完成）
public class DiningTableService { //业务层

    //定义一个DiningTableDAO对象
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回所有餐桌的信息
    public List<DiningTable> list() {
        return diningTableDAO.queryMulti("select id, state from diningTable", DiningTable.class);
    }

    //根据id，查询对应的餐桌DiningTable对象，如果返回null，表示餐桌不存在
    public DiningTable getDiningTableById(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
    }

    //如果餐桌可以预定，调用此方法，对其状态进行更新，包括预定人的名字和电话
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update = diningTableDAO.update("update diningTable set state = '已预定', " +
                "orderName = ?, orderTel = ? where id = ?", orderName, orderTel, id);
        return update > 0;
    }

    //更新餐桌状态的方法
    public boolean updateDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ? where id = ?", state, id);
        return update > 0;
    }

    //提供方法，将指定的餐桌设置为空闲状态
    public boolean updateDiningTableToFree(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ?, " +
                "orderName = '', orderTel = '' where id = ?", state, id);
        return update > 0;
    }
}
