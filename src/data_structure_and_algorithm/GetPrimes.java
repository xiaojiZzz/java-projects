package data_structure_and_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 筛选质数的两种方法，以及判断一个数是否是质素
public class GetPrimes {
    // 埃氏筛，时间复杂度：O(nloglogn) 判断在 [2, n] 之间所有的质数
    public boolean[] eratosthenesSieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    // 线性筛，时间复杂度：O(n) 返回在 [2, n] 之间所有的质数
    public List<Integer> linearSieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
            for (int prime : primes) {
                if (i * prime > n) {
                    break;
                }
                isPrime[i * prime] = false;
                if (i % prime == 0) {
                    break;
                }
            }
        }
        return primes;
    }

    // 判断 n 是否是质数，如果判断一群数是不是质数，需要用到上面的两种方法，这里直接使用 试除法
    // 时间复杂度 O(sqrt(n))
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 优化上面判断质数的方法，利用质数的特性，进一步减少检查的次数。
    // 除了 2 和 3 之外，所有质数都可以表示为 6k ± 1 的形式，其中 k 是正整数。
    // 时间复杂度在最坏的情况下仍为 O(sqrt(n))，但平均时间复杂度优于 O(sqrt(n))
    public boolean isPrime2(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
