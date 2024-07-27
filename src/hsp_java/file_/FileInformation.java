package hsp_java.file_;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@SuppressWarnings({"all"})
public class FileInformation {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/jijunwei/Downloads/Test3.txt";
//        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName, true), "gbk");
//        osw.write("加油");
//        osw.close();
//        System.out.println("完成保存");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "gbk");
        BufferedReader bufferedReader = new BufferedReader(isr);
        String s = bufferedReader.readLine();
        System.out.println(s);

    }
}
