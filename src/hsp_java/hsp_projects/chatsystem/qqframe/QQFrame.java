package hsp_java.hsp_projects.chatsystem.qqframe;

import hsp_java.hsp_projects.chatsystem.qqserver.service.QQServer;

//该类创建QQServer，启动后台服务
public class QQFrame {
    public static void main(String[] args) {

        new QQServer();
    }
}