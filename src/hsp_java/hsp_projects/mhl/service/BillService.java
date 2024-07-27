package hsp_java.hsp_projects.mhl.service;

import hsp_java.hsp_projects.mhl.dao.BillDAO;
import hsp_java.hsp_projects.mhl.dao.MultiTableDAO;
import hsp_java.hsp_projects.mhl.domain.Bill;
import hsp_java.hsp_projects.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

//该类完成对bill表的各种操作（通过调用BillDAO对象完成）
public class BillService {

    //定义一个 BillDAO 属性
    private BillDAO billDAO = new BillDAO();
    //定义一个 MenuService 属性
    private MenuService menuService = new MenuService();
    //定义一个 DiningTableService 属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义一个 MultiTableDAO 属性
    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    //编写点餐方法
    //1.生成账单
    //2.需要更新对应餐桌的状态
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        //生成一个账单号，利用 UUID
        String billId = UUID.randomUUID().toString();

        //将账单生成到bill表，要求直接计算账单金额
        int update = billDAO.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), '未结账')",
                billId, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);
        if (update <= 0) {
            return false;
        }

        //更新对应餐桌的状态
        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

    //返回所有的账单，提供给View使用
    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    //返回所有的账单(包括菜品名和价格)，提供给View使用
    public List<MultiTableBean> list2() {
        return multiTableDAO.queryMulti("select bill.*, name, price from bill, menu where bill.menuId = menu.id",
                MultiTableBean.class);
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill = billDAO.querySingle("select * from bill where diningTableId = ? and state = '未结账' limit 0, 1",
                Bill.class, diningTableId);
        return bill != null;
    }

    //如果餐桌存在，且该餐桌有未结账的账单，就完成结账
    public boolean payBill(int diningTableId, String payMode) {
        //1.修改bill表
        int update = billDAO.update("update bill set state = ? where diningTableId = ? and state = '未结账'",
                payMode, diningTableId);
        if (update <= 0) {
            return false;
        }

        //2.修改diningTable表
        if (!diningTableService.updateDiningTableToFree(diningTableId, "空")) {
            return false;
        }

        return true;
    }
}
