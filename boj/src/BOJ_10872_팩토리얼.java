import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10872_팩토리얼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = factorial(N);
        System.out.println(answer);
    }

    private static int factorial(int n) {
        if(n == 1 || n == 0)
            return 1;

        return n * factorial(n - 1);
    }
}
