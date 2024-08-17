package data_structure_and_algorithm;

// 辗转相除法求最大公约数（GCD），通过 GCD 计算 LCM（最小公倍数）
public class GCDAndLCM {
    // 计算最大公约数（GCD）
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 计算最小公倍数（LCM）
    public int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
