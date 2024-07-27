package design_patterns.principles.open_close_principle;

//开闭原则：对扩展开放，对修改关闭。想要达到这样的效果，我们需要使用接口和抽象类。
//在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。
public class Client {
    public static void main(String[] args) {
        //1.创建搜狗输入法对象
        SougouInput sougouInput = new SougouInput();
        //2.创建皮肤对象
//        DefaultSkin defaultSkin = new DefaultSkin();
        HeiMaSkin heiMaSkin = new HeiMaSkin();
        //3.将皮肤设置到输入法中
        sougouInput.setSkin(heiMaSkin);
        //4.显示皮肤
        sougouInput.display();
    }
}
