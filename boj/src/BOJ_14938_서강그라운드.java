import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938_서강그라운드 {

    static int N, M, R, max = Integer.MIN_VALUE;
    static int[][] dist;
    static int[] items, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        items = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 15 * 15 + 1);
            dist[i][i] = 0;
        }

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            dist[from][to] = weight;
            dist[to][from] = weight;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] <= M) {
                    sum[i] += items[j];
                }
            }
            max = Math.max(max, sum[i]);
        }

        System.out.println(max);
    }
}
