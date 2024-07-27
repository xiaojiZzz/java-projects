package hsp_java.hsp_projects.chatsystem.qqclient.service;

import hsp_java.hsp_projects.chatsystem.qqcommon.Message;
import hsp_java.hsp_projects.chatsystem.qqcommon.MessageType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//该类完成文件的传输服务
public class FileClientService {

    /**
     * @param src      源文件
     * @param dest     把该文件传输到对方的目录地址
     * @param senderId 发送者
     * @param getterId 接收者
     */
    public void sendFileToOne(String src, String dest, String senderId, String getterId) {

        //读src文件 -> message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);

        //需要将文件读取
        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int) new File(src).length()];

        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes); //将src文件读入到程序的字节数组
            //将文件的字节数组设置到message中
            message.setFileBytes(fileBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭文件流
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //提示信息
        System.out.println("\n" + senderId + " 给 " + getterId + " 发送文件：" + src + " 到对方的电脑目录 " + dest);

        //发送信息
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.
                    getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
