package design_patterns.struct_patterns.bridge;

//扩展抽象化角色，Windows操作系统
public class Windows extends OperatingSystem {
    public Windows(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void play(String fileName) {
        videoFile.decode(fileName);
    }
}
