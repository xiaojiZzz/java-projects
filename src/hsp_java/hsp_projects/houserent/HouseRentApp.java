package hsp_java.hsp_projects.houserent;

import hsp_java.hsp_projects.houserent.view.HouseView;

public class HouseRentApp {
    public static void main(String[] args) {
        // 创建HouseView对象，并且显示主菜单，是整个程序的入口。
        new HouseView().mainMenu();
        System.out.println("--------你退出了房屋出租系统--------");
    }
}
