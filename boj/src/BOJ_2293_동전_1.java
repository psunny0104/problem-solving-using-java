import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293_동전_1 {

    static int N, K;
    static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        dp = new int[K + 1];
        dp[0] = 1;

        for (int idx = 0; idx < N; idx++) {
            for (int sumTarget = coins[idx]; sumTarget <= K; sumTarget++) {
                dp[sumTarget] = dp[sumTarget] + dp[sumTarget - coins[idx]];
            }
        }

        System.out.println(dp[K]);
    }

}
