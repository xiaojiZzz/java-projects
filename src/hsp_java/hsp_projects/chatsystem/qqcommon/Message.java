package hsp_java.hsp_projects.chatsystem.qqcommon;

import java.io.Serializable;

//表示客户端和服务端通信时的消息对象
public class Message implements Serializable {

    private static final long serialVersionUID = 1L; //为了增强序列化的兼容性
    private String sender; //表示发送者
    private String getter; //表示接收者
    private String content; //表示发送的内容
    private String sendTime; //表示发送时间
    private String mesType; //表示消息的类型

    //进行扩展和文件相关的数组
    private byte[] fileBytes;
    private int fileLen = 0;
    private String dest; //文件传输到的地方
    private String src; //源文件路径

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public Message() {
    }

    public Message(String sender, String getter, String content, String sendTime) {
        this.sender = sender;
        this.getter = getter;
        this.content = content;
        this.sendTime = sendTime;
    }

}
