import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1495_기타리스트 {

    static int N, S, M;
    static int[] V;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][M + 1]; // i 번째에 j번째 수 등장 여부
        dp[0][S] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i - 1][j]) {
                    int plus = j + V[i];
                    int minus = j - V[i];
                    if (plus >= 0 && plus <= M) {
                        dp[i][plus] = true;
                    }
                    if (minus >= 0 && minus <= M) {
                        dp[i][minus] = true;
                    }
                }
            }
        }

        int max = -1;
        for (int i = 0; i <= M; i++) {
            if (dp[N][i]) {
                max = i;
            }
        }
        System.out.println(max);

    }

}
