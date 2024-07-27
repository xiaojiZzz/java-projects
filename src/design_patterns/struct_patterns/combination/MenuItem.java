package design_patterns.struct_patterns.combination;

//菜单项类，属于叶子结点
public class MenuItem extends MenuComponent{

    public MenuItem(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        //打印菜单项的名称
        System.out.println(name);
    }
}
