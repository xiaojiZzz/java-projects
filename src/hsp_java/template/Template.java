package hsp_java.template;

/**
 * 抽象类—模版设计模式
 */
abstract public class Template {
    public abstract void job();

    public void calculateTime() {
        // 得到开始时间
        long start = System.currentTimeMillis();
        job(); // 动态绑定
        // 得到结束时间
        long end = System.currentTimeMillis();
        System.out.println("执行时间为：" + (end - start));
    }
}

