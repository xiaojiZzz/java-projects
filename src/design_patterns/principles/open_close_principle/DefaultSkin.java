package design_patterns.principles.open_close_principle;

//默认输入法皮肤
public class DefaultSkin extends AbstractSkin {

    @Override
    public void display()  {
        System.out.println("默认皮肤");
    }
}
