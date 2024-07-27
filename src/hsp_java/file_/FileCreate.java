package hsp_java.file_;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileCreate {
    public static void main(String[] args) {

    }

    //方式一 new File(String pathname)
    @Test
    public void create01() {
        String filePath = "/Users/jijunwei/Downloads/新建文件1.txt";
        File file = new File(filePath);

        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //方式二 new File(File parent, String child)
    @Test
    public void create02() {
        File parentFile = new File("/Users/jijunwei/Downloads/");
        String fileName = "新建文件2.txt";
        File file = new File(parentFile, fileName);

        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //方式三 new File(String parent, String child)
    @Test
    public void create03() {
        String parentPath = "/Users/jijunwei/Downloads/";
        String childPath = "新建文件3.txt";
        File file = new File(parentPath, childPath);

        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

