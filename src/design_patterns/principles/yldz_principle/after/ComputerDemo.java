package design_patterns.principles.yldz_principle.after;

//依赖倒转原则：高层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象。
// 简单的说就是要求对抽象进行编程，不要对实现进行编程，这样就降低了客户与实现模块间的耦合。
public class ComputerDemo {

    public static void main(String[] args) {
        //创建组件
        HardDisk hardDisk = new XiJieHardDisk();
        CPU cpu = new IntelCPU();
        Memory memory = new KingstonMemory();
        //创建计算机对象
        Computer computer = new Computer();
        //组装计算机
        computer.setCpu(cpu);
        computer.setMemory(memory);
        computer.setHardDisk(hardDisk);
        //运行计算机
        computer.run();
    }
}
