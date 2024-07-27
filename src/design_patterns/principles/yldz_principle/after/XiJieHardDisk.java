package design_patterns.principles.yldz_principle.after;

public class XiJieHardDisk implements HardDisk{

    //存储数据的方法
    public void save(String data) {
        System.out.println("使用希捷硬盘存储数据为" + data);
    }

    //获取数据的方法
    public String get() {
        System.out.println("使用希捷硬盘获取数据");
        return "数据";
    }
}
