import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1463_1로_만들기 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N * 3 + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i = 1; i <= N; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
        }

        System.out.println(dp[N]);

    }
}
