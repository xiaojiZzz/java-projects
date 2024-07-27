package design_patterns.behave_patterns.template;

//抽象类，定义模版方法和基本方法
public abstract class AbstractClass {

    //模版方法，不能被重写，用final来修饰
    public final void cookProcess() {
        pourOil();
        heatOil();
        pourVegetable();
        pourSauce();
        fry();
    }

    //第一步，倒油是一样的，所以直接实现
    public void pourOil() {
        System.out.println("倒油");
    }

    //第二步，热油是一样的，所以直接实现
    public void heatOil() {
        System.out.println("热油");
    }

    //第三步，倒蔬菜是不一样的(一个下包菜，一个下菜心）
    public abstract void pourVegetable();

    //第四步，倒调味料是不一样的
    public abstract void pourSauce();

    //第五步，翻炒是一样的，所以直接实现
    public void fry() {
        System.out.println("炒啊炒啊炒到熟啊");
    }
}
