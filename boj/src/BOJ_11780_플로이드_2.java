import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11780_플로이드_2 {

    static int V, E;
    static int LIMIT = 100_000_000;
    static int[][] d, next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        d = new int[V + 1][V + 1];
        next = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(d[i], LIMIT);
            d[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            d[from][to] = Math.min(d[from][to], weight);
            next[from][to] = to;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                sb.append(d[i][j] == LIMIT ? 0 : d[i][j]).append(" ");
            }
            sb.append("\n");
        }

        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (next[i][j] == 0) {
                    sb.append(0).append("\n");
                    continue;
                }
                path.clear();
                int start = i;
                while (start != j) {
                    path.add(start);
                    start = next[start][j];
                }
                path.add(j);
                sb.append(path.size()).append(" ");
                for (Integer integer : path) {
                    sb.append(integer).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

}
