import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1644_소수의_연속합 {

    static int N;
    static List<Integer> primeNumbers = new ArrayList<>();
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 소수 만들기
        findPrimeNumbers();

        // 1일 때 처리
        int limit = primeNumbers.size();
        if (limit == 0) {
            System.out.println(0);
            return;
        }

        int start = 0;
        int end = 0;
        int sum = primeNumbers.get(start);
        int answer = 0;

        while (start != limit) {
            if (sum >= N) {
                if (sum == N) {
                    answer++;
                }
                sum -= primeNumbers.get(start);
                start++;
            } else {
                end++;
                if (end == limit) {
                    break;
                }
                sum += primeNumbers.get(end);
            }
        }
        System.out.println(answer);
    }

    private static void findPrimeNumbers() {
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i + i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }
    }
}
