import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023_신기한_소수 {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        search(1, 2);
        search(1, 3);
        search(1, 5);
        search(1, 7);

        System.out.print(sb);
    }

    private static void search(int cnt, int number) {
        if (cnt == N) {
            if (!isPrime(number)) {
                return;
            }
            sb.append(number).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i++) {
            // 짝수일 때 처리
            if (i % 2 == 0) {
                continue;
            }

            int newNumber = number * 10 + i;

            if (!isPrime(newNumber)) {
                continue;
            }
            search(cnt + 1, newNumber);
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
