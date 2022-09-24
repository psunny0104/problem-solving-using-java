import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010_다리_놓기 {

    // mCn
    static int T, M, N, LIMIT = 30, answer;
    static int[][] dp = new int[LIMIT + 1][LIMIT + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i <= LIMIT; i++) {
            dp[i][1] = i;
        }

        for (int m = 2; m <= LIMIT; m++) {
            for (int n = 2; n <= LIMIT; n++) {
                dp[m][n] = dp[m - 1][n - 1] + dp[m - 1][n];
            }
        }

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            answer = 0;
            sb.append(dp[M][N]).append("\n");
        }
        System.out.print(sb);
    }
}
