import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9095_123더하기 {

    static int T, N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        dp = new int[12];
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            Arrays.fill(dp, 0);
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            for (int i = 4; i <= N; i++) {
                dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
            }

            sb.append(dp[N]).append("\n");
        }
        System.out.print(sb);
    }

}
