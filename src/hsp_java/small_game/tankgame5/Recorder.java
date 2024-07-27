package hsp_java.small_game.tankgame5;

import java.io.*;
import java.util.Vector;

@SuppressWarnings({"all"})
//该类用于记录相关的信息，会和文件交互
public class Recorder {

    //定义变量，记录我方击毁敌方坦克数
    private static int allEnemyTankNum = 0;
    //定义IO对象
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "/Users/jijunwei/JavaProjects/src/hsp_java.small_game/tankgame5/myRecord.txt";
    //定义Vector，指向 MyPanel 对象的敌人坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;
    //定义一个Node的Vector，用于保存敌人的信息
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //返回记录文件的路径
    public static String getRecordFile() {
        return recordFile;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克击毁一辆敌方坦克，allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        allEnemyTankNum++;
    }

    //增加一个方法，用于读取recordFile，恢复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取文件，生成 Nodes 集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

    //当游戏退出时，我们将 allEnemyTankNum 保存到 recordFile 文件中
    //保存敌人坦克的坐标和方向
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            //遍历敌人坦克集合，根据未被消灭的敌人，保存其坐标和方向
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出敌人坦克，将其保存
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) { //建议判断
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入文件
                    bw.write(record + "\r\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
