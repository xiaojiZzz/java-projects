package hsp_java.hsp_projects.houserent.service;

import hsp_java.hsp_projects.houserent.domain.House;

/**
 * 业务层
 * 1.定义House[]，保存House对象
 * 2.响应HouseView的调用
 * 3.完成对房屋信息的各种操作（crud：create、read、update、delete）
 */
public class HouseService {

    private House[] houses; // 保存House对象
    private int houseNums = 1; // 记录当前有多少个房屋信息
    private int idCounter = 1; // 记录当前的id值

    public HouseService(int size) {
        // new houses
        houses = new House[size]; // 当创建HouseService对象时，指定数组大小
        houses[0] = new House(1, "jack", "122", "海淀区", 2000, "未出租");
    }

    // list方法，返回houses
    public House[] list() {
        return houses;
    }

    // add方法，添加新对象，返回boolean
    public boolean add(House newHouse) {
        // 判断是否还可以添加(暂时不考虑扩容的问题)
        if (houseNums == houses.length) {
            System.out.println("房源已满，无法继续添加。");
            return false;
        }
        // 把newHouse对象加入到房源，新增加了一个房屋
        houses[houseNums++] = newHouse;
        // 需要一个id自增长机制,然后更新id
        newHouse.setId(++idCounter);
        return true;
    }

    // del方法，删除一个房屋信息
    public boolean del(int delId) {
        // 应先找到要删除房屋信息对应的下标
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) { //说明delId在房源中不存在
            return false;
        }
        // 如果找到
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseNums] = null;
        return true;
    }

    // findById方法,返回House对象或是null
    public House findById(int findId) {
        // 遍历数组
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }
}
