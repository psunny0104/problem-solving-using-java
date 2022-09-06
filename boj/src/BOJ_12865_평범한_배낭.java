import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한_배낭 {

    static int N, K;
    static int[][] dp;
    static Thing[] things;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        things = new Thing[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            things[i] = new Thing(w, v);
        }

        dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - things[i].weight >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j],
                            dp[i - 1][j - things[i].weight] + things[i].value);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

    private static class Thing {

        int weight;
        int value;

        public Thing(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
