import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11047_동전_0 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(in.nextToken());
        int K = Integer.parseInt(in.nextToken());
        int[] coins = new int[N];

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        for (int i = N - 1; i >= 0; i--) {
            answer += K / coins[i];
            K = K % coins[i];
//            if (K <= 0) {
//                break;
//            }
        }

        System.out.println(answer);

    }
}
