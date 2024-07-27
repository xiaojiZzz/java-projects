package java_learning.gui;

import javax.swing.*;

public class SimpleGui1 {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JButton button = new JButton("click me");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭时，程序结束

        frame.getContentPane().add(button); //把button加到frame上
        frame.setSize(300, 300); //设置frame大小
        frame.setVisible(true); //把frame显示出来
    }
}
