package java_learning;

public class VarParameter {
    public static void main(String[] args) {
        SumMethod method = new SumMethod();
        int res = method.sum(3, 3, 2, 5); //细节：可变参数的实参可以是数组
        System.out.println("相加的结果为" + res);
        int[] arr = {1, 2, 3, 5};
        int res1 = method.sum(arr);
        System.out.println("放入数组后结果为" + res1);
//        细节：可变参数和普通参数（可以是和可变参数不一样的数据类型）放在一起时候，可变参数要放在最后
//        一个形参列表里最多只能有一个可变参数
        String totalScore = method.showScore("jack", 100, 98, 99, 95, 96, 95);
        System.out.println(totalScore);
    }
}

class SumMethod {
    public int sum(int... nums) {
//        Scanner scanner = new Scanner(System.in);
//        int... 表示接收 0-n 个int参数
//        使用可变参数时候，可以当作数组来使用，即 nums 可以当作是数组
//        System.out.println("参数个数为" + nums.length);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += nums[i];
        }
        return res;
    }

    public String showScore(String name, double... score) {
        double totalScore = 0;
        for (int i = 0; i < score.length; i++) {
            totalScore += score[i];
        }
//        可以通过 + 来拼接 返回String
        return name + "有" + score.length + "门课程，总分为" + totalScore;
    }
}