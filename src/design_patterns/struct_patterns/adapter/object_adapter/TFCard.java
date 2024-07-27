package design_patterns.struct_patterns.adapter.object_adapter;

//适配者的接口
public interface TFCard {

    //从TFCard中读取数据
    String readTF();
    //从TFCard中写数据
    void writeTF(String msg);
}
