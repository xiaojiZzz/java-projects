package design_patterns.principles.yldz_principle.before;

//问题：组装的电脑的cpu只能是Intel的，内存条只能是金士顿的，硬盘只能是希捷的，
// 这对用户肯定是不友好的，用户有了机箱肯定是想按照自己的喜好，选择自己喜欢的配件。
public class ComputerDemo {
    public static void main(String[] args) {
        //创建组件
        XiJieHardDisk hardDisk = new XiJieHardDisk();
        IntelCPU cpu = new IntelCPU();
        KingstonMemory memory = new KingstonMemory();
        //创建计算机对象
        Computer computer = new Computer();
        //组装计算机
        computer.setIntelCPU(cpu);
        computer.setKingstonMemory(memory);
        computer.setXiJieHardDisk(hardDisk);
        //运行计算机
        computer.run();
    }

}
