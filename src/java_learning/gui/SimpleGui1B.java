package java_learning.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui1B implements ActionListener {
    JButton button;

    public static void main(String[] args) {
        SimpleGui1B gui = new SimpleGui1B();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        button = new JButton("click me"); //创建button
        button.addActionListener(this); //使按钮可以监听用户事件
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭时，程序结束
        frame.getContentPane().add(button); //把button加到frame上
        frame.setSize(300, 300); //设置frame大小
        frame.setVisible(true); //把frame显示出来
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setText("I've been clicked!");
    }
}
