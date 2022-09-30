import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17069_파이프_옮기기_2 {

    static int N;
    static int[][] map;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N + 1][N + 1][3];
        dp[1][2][0] = 1;
        dp[1][2][1] = 0;
        dp[1][2][2] = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 3; c <= N; c++) {
                // 가로
                if (map[r][c] == 0 && c - 1 >= 1) {
                    dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];
                }
                // 세로
                if (map[r][c] == 0 && r - 1 >= 1) {
                    dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];
                }
                if (r - 1 < 1 || c - 1 < 1) {
                    continue;
                }
                // 대각
                if (map[r][c] == 0 && map[r - 1][c] == 0 && map[r][c - 1] == 0) {
                    dp[r][c][2] = dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
                }
            }
        }

        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }

}
