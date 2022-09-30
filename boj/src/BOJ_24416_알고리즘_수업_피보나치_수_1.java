import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_24416_알고리즘_수업_피보나치_수_1 {

    static int N, fibCallCnt, fibonacciCallCnt;
    static int[] f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        f = new int[N + 1];

        fib(N);
        fibonacci(N);

        System.out.println(fibCallCnt + " " + fibonacciCallCnt);

    }

    private static int fibonacci(int n) {
        f[1] = f[2] = 1;
        for (int i = 3; i <= N; i++) {
            fibonacciCallCnt++;
            f[i] = f[i - 1] + f[i - 2];
        }

        return f[n];
    }

    private static int fib(int n) {
        if (n == 1 || n == 2) {
            fibCallCnt++;
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }


}
