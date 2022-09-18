import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403_경로_찾기 {

    static int V, LIMIT = 101;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        d = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= V; j++) {
                d[i][j] = Integer.parseInt(st.nextToken());
                if (d[i][j] == 0) {
                    d[i][j] = LIMIT;
                }
            }
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                sb.append(d[i][j] == LIMIT ? 0 : 1).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

}
