import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1929_소수_구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine(), " ");

        int st = Integer.parseInt(in.nextToken());
        int ed = Integer.parseInt(in.nextToken());
        Boolean[] isPrime = new Boolean[ed + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        getPrimeNumber(isPrime, ed);
        print(st, ed, isPrime);
    }

    private static void print(int st, int ed, Boolean[] isPrime) {
        StringBuilder sb = new StringBuilder();
        for (int i = st; i <= ed; i++) {
            if (isPrime[i] == false) {
                continue;
            }
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static void getPrimeNumber(Boolean[] isPrime, int ed) {
        for (int i = 2; i <= ed; i++) {
            if (isPrime[i] == false) {
                continue;
            }

            for (int j = i + i; j <= ed; j += i) {
                isPrime[j] = false;
            }

        }
    }

}
