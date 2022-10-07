import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9205_맥주_마시면서_걸어가기 {

    static int T, N, LIMIT = 110_000;
    static node[] nodes;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            d = new int[N + 3][N + 3];
            for (int i = 1; i <= N + 2; i++) {
                Arrays.fill(d[i], LIMIT);
                d[i][i] = d[i][0] = 0;
            }

            nodes = new node[N + 3];
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            nodes[1] = new node(r, c);

            for (int i = 2; i <= N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                c = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
                nodes[i] = new node(r, c);
            }

            for (int i = 1; i <= N + 2; i++) {
                for (int j = 1; j <= N + 2; j++) {
                    int curDist = getDistance(nodes[i], nodes[j]);
                    if (curDist <= 1000) {
                        d[i][j] = d[j][i] = curDist;
                    }
                }
            }

            for (int k = 1; k <= N + 2; k++) {
                for (int i = 1; i <= N + 2; i++) {
                    for (int j = 1; j <= N + 2; j++) {
                        if (d[i][k] <= 1000 && d[k][j] <= 1000) {
                            d[i][j] = Math.min(d[i][j], 1000);
                        }
                    }
                }
            }

            if (d[1][N + 2] <= 1000) {
                sb.append("happy");
            } else {
                sb.append("sad");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static int getDistance(node st, node dst) {
        return Math.abs(st.r - dst.r) + Math.abs(st.c - dst.c);
    }

    private static class node {

        int r, c;

        public node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
