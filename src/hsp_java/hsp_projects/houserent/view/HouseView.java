package hsp_java.hsp_projects.houserent.view;

import hsp_java.hsp_projects.houserent.domain.House;
import hsp_java.hsp_projects.houserent.service.HouseService;
import hsp_java.hsp_projects.houserent.utils.Utility;

/**
 * 1.显示界面
 * 2.接收用户输入
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {

    private boolean loop = true; // 控制显示菜单
    private char key = ' '; // 接收用户选择的哪个菜单
    private HouseService houseService = new HouseService(10);

    // 显示主菜单
    public void mainMenu() {
        do {
            System.out.println("\n----------房屋出租系统菜单----------");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 源");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 显 示 房 屋 信 息");
            System.out.println("\t\t\t6 退      出");
            System.out.print("请输入您的选择（1-6）：");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    this.addHouse();
                    break;
                case '2':
                    this.findHouse();
                    break;
                case '3':
                    this.delHouse();
                    break;
                case '4':
                    this.update();
                    break;
                case '5':
                    this.listHouses();
                    break;
                case '6':
                    this.exit();
                    break;
            }
        } while (loop);
    }

    // 编写listHouses()显示房屋列表
    public void listHouses() {
        System.out.println("--------------房屋列表--------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) {
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("----------房屋列表显示完毕----------");
    }

    // 编写addHouse()接收输入，创建House对象，调用add方法
    public void addHouse() {
        System.out.println("--------------添加房屋--------------");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);
        // 创建一个新的House对象,id是系统分配的，用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("------------添加房屋成功------------");
        } else {
            System.out.println("------------添加房屋失败------------");
        }
    }

    // 编写delHouse方法，接收要删除的id，调用Service的del方法
    public void delHouse() {
        System.out.println("------------删除房屋信息------------");
        System.out.print("请输入待删除的房屋id(-1退出)：");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("----------放弃删除房屋信息----------");
            return;
        }
        char choice = Utility.readConfirmSelection(); // 注意，该方法就有循环判断的逻辑
        if (choice == 'Y') {
            if (houseService.del(delId)) {
                System.out.println("----------删除房屋信息成功----------");
            } else {
                System.out.println("-------房屋编号不存在，删除失败-------");
            }
        } else {
            System.out.println("----------放弃删除房屋信息----------");
        }
    }

    // 退出确认
    public void exit() {
        // 使用工具包
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }

    // 根据id查找相应的房源
    public void findHouse() {
        System.out.println("--------------查找房源--------------");
        System.out.print("请输入要查找的id：");
        int findId = Utility.readInt();
        // 调用方法
        House house = houseService.findById(findId);
        if (house != null) {
            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
            System.out.println(house);
        } else {
            System.out.println("----------没有查到房源信息----------");
        }
    }

    // 根据id修改房屋信息
    public void update() {
        System.out.println("------------修改房屋信息------------");
        System.out.print("请选择待修改房屋的编号（-1退出）：");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("----------放弃修改房屋信息----------");
            return;
        }
        // 根据updateId查找对象
        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("-------房屋编号不存在，修改失败-------");
            return;
        }
        System.out.print("房主（" + house.getName() + "）：");
        String name = Utility.readString(8, ""); // 如果这里用户直接回车表示不修改，直接给默认值""
        if (!"".equals(name)) {
            house.setName(name);
        }
        System.out.print("电话（" + house.getPhone() + "）：");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }
        System.out.print("地址（" + house.getAddress() + "）：");
        String address = Utility.readString(16, "");
        if (!"".equals(address)) {
            house.setAddress(address);
        }
        System.out.print("月租（" + house.getRent() + "）：");
        int rent = Utility.readInt(-1);
        if (rent != -1) {
            house.setRent(rent);
        }
        System.out.print("状态（" + house.getState() + "）：");
        String state = Utility.readString(3, "");
        if (!"".equals(state)) {
            house.setState(state);
        }
        System.out.println("----------修改房屋信息成功----------");
    }
}
