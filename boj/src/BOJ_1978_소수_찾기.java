import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1978_소수_찾기 {

    static boolean[] isPrime;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(numbers);

        isPrime = new boolean[numbers[N - 1] + 1];
        Arrays.fill(isPrime, true);

        makePrime(numbers[N - 1]);
        answer = (int) Arrays.stream(numbers).filter(i -> isPrime[i]).count();

        System.out.println(answer);
    }

    private static void makePrime(int target) {
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= target; i++) {
            if (!isPrime[i]) {
                continue;
            }

            for (int j = i + i; j <= target; j += i) {
                isPrime[j] = false;
            }
        }
    }
}
