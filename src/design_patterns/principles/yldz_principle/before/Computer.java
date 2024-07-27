package design_patterns.principles.yldz_principle.before;

public class Computer {

    private XiJieHardDisk xiJieHardDisk;
    private IntelCPU intelCPU;
    private KingstonMemory kingstonMemory;

    public XiJieHardDisk getXiJieHardDisk() {
        return xiJieHardDisk;
    }

    public void setXiJieHardDisk(XiJieHardDisk xiJieHardDisk) {
        this.xiJieHardDisk = xiJieHardDisk;
    }

    public IntelCPU getIntelCPU() {
        return intelCPU;
    }

    public void setIntelCPU(IntelCPU intelCPU) {
        this.intelCPU = intelCPU;
    }

    public KingstonMemory getKingstonMemory() {
        return kingstonMemory;
    }

    public void setKingstonMemory(KingstonMemory kingstonMemory) {
        this.kingstonMemory = kingstonMemory;
    }

    public void run() {
        System.out.println("运行计算机");
        String data = xiJieHardDisk.get();
        System.out.println("从硬盘上获取的数据是：" + data);
        intelCPU.run();
        kingstonMemory.save();
    }
}
