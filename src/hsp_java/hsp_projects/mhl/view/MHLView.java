package hsp_java.hsp_projects.mhl.view;

import hsp_java.hsp_projects.mhl.domain.*;
import hsp_java.hsp_projects.mhl.service.BillService;
import hsp_java.hsp_projects.mhl.service.DiningTableService;
import hsp_java.hsp_projects.mhl.service.EmployeeService;
import hsp_java.hsp_projects.mhl.service.MenuService;
import hsp_java.hsp_projects.mhl.utils.Utility;

import java.util.List;

@SuppressWarnings({"all"})
//这是主界面
public class MHLView {

    //控制是否退出菜单
    private boolean loop = true;
    private String key = ""; //接收用户的选择
    //定义EmployeeService属性
    private EmployeeService employeeService = new EmployeeService();
    //定义DiningTableService的属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义MenuService的属性
    private MenuService menuService = new MenuService();
    //定义BillService的属性
    private BillService billService = new BillService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    //显示主菜单
    public void mainMenu() {

        while (loop) {
            System.out.println("============满汉楼============");
            System.out.println("\t\t 1 登陆满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);

            switch (key) {
                case "1":
                    System.out.print("输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.print("输入密  码：");
                    String pwd = Utility.readString(50);
                    //到数据库去判断
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
                    if (employee != null) {
                        System.out.println("==========登陆成功[" + employee.getName() + "]==========");
                        //显示二级菜单
                        while (loop) {
                            System.out.println("\n==========满汉楼(二级菜单)==========");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出满汉楼");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);

                            switch (key) {
                                case "1":
                                    listDiningTable(); //显示餐桌状态
                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你输入有误，请重新输入。");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("============登陆失败============");
                    }
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误，请重新输入。");
            }
        }
        System.out.println("你退出了满汉楼系统~");
    }

    //显示所有餐桌状态
    public void listDiningTable() {
        List<DiningTable> list = diningTableService.list();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }
        System.out.println("============显示完毕============");
    }

    //完成订座
    public void orderDiningTable() {
        System.out.println("============预定餐桌============");
        System.out.print("请选择要预定的餐桌编号(-1退出)：");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("============取消预定============");
            return;
        }
        //该方法得到的是Y或者N
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            //根据orderId返回对应的DiningTable对象，如果为null，说明对象不存在
            DiningTable diningTable = diningTableService.getDiningTableById(orderId);
            if (diningTable == null) {
                System.out.println("==========预定餐桌不存在==========");
                return;
            }
            if (!("空".equals(diningTable.getState()))) { //说明餐桌不能预定
                System.out.println("==========餐桌无法预定==========");
                return;
            }
            //这时说明可以真的预定，更新餐桌状态
            System.out.print("预定人的名字：");
            String orderName = Utility.readString(50);
            System.out.print("预定人的电话：");
            String orderTel = Utility.readString(50);
            //更新餐桌信息
            if (diningTableService.orderDiningTable(orderId, orderName, orderTel)) {
                System.out.println("============预定成功============");
            } else {
                System.out.println("============预定失败============");
            }
        } else {
            System.out.println("============取消预定============");
            return;
        }
    }

    //显示所有菜品
    public void listMenu() {
        List<Menu> list = menuService.list();
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t价格");
        for (Menu menu : list) {
            System.out.println(menu);
        }
        System.out.println("============显示完毕============");
    }

    //完成点餐
    public void orderMenu() {
        System.out.println("============点餐服务============");
        System.out.print("请输入点餐的餐桌号(-1退出)：");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("============取消点餐============");
            return;
        }
        //验证餐桌号是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(orderDiningTableId);
        if (diningTable == null) {
            System.out.println("==========餐桌号不存在==========");
            return;
        }
        System.out.print("请输入点餐的菜品号(-1退出)：");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("============取消点餐============");
            return;
        }
        //验证菜品编号是否存在
        Menu menu = menuService.getMenuById(orderMenuId);
        if (menu == null) {
            System.out.println("==========菜品号不存在==========");
            return;
        }
        System.out.print("请输入点餐的菜品量(-1退出)：");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("============取消点餐============");
            return;
        }

        //点餐
        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("============点餐成功============");
        } else {
            System.out.println("============点餐失败============");
        }
    }

    //显示账单信息
    public void listBill() {
        List<MultiTableBean> bills = billService.list2();
        System.out.println("\n编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t菜品名\t\t价格");
        for (MultiTableBean bill : bills) {
            System.out.println(bill);
        }
        System.out.println("============显示完毕============");
    }

    //完成结账
    public void payBill() {
        System.out.println("============结账服务============");
        System.out.print("请选择要结账的餐桌编号(-1退出)：");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) {
            System.out.println("============取消结账============");
            return;
        }
        //验证餐桌是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(diningTableId);
        if (diningTable == null) {
            System.out.println("===========餐桌不存在===========");
            return;
        }
        //验证餐桌是否需要结账
        if (!billService.hasPayBillByDiningTableId(diningTableId)) {
            System.out.println("==========餐桌不需要结账==========");
            return;
        }
        System.out.print("结账方式(现金/支付宝/微信)回车表示退出：");
        String payMode = Utility.readString(20, ""); //如果回车，就返回""
        if ("".equals(payMode)) {
            System.out.println("============取消结账============");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            //结账
            if (billService.payBill(diningTableId, payMode)) {
                System.out.println("============完成结账============");
            } else {
                System.out.println("============结账失败============");
            }
        } else {
            System.out.println("============取消结账============");
        }
    }
}
