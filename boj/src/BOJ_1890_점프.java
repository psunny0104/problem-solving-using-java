import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890_점프 {

    static int N;
    static long[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new long[N + 1][N + 1];
        dp = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Long.parseLong((st.nextToken()));
            }
        }

        dp[1][1] = 1;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (dp[r][c] == 0) {
                    continue;
                }

                if (r == N && c == N) {
                    break;
                }

                long nr = r + map[r][c];
                long nc = c + map[r][c];

                if (nr <= N && c <= N) {
                    dp[(int) nr][c] += dp[r][c];
                }
                if (nc <= N && r <= N) {
                    dp[r][(int) nc] += dp[r][c];
                }
            }
        }

        System.out.println(dp[N][N]);

    }

}
