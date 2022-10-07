import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5643_키_순서 {

    static int T, V, E;
    static boolean[][] naturalDist; // 정방향
    static boolean[][] reverseDist; // 역방향
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            V = Integer.parseInt(br.readLine());
            E = Integer.parseInt(br.readLine());

            naturalDist = new boolean[V + 1][V + 1];
            reverseDist = new boolean[V + 1][V + 1];
            for (int i = 0; i <= V; i++) {
                Arrays.fill(naturalDist[i], false);
                Arrays.fill(reverseDist[i], false);
                naturalDist[i][i] = true;
                reverseDist[i][i] = true;
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                naturalDist[from][to] = true;
                reverseDist[to][from] = true;
            }

            for (int k = 1; k <= V; k++) {
                for (int i = 1; i <= V; i++) {
                    for (int j = 1; j <= V; j++) {
                        if (naturalDist[i][k] && naturalDist[k][j]) {
                            naturalDist[i][j] = true;
                        }
                        if (reverseDist[i][k] && reverseDist[k][j]) {
                            reverseDist[i][j] = true;
                        }
                    }
                }
            }

            int vertexCnt = 0;
            for (int i = 1; i <= V; i++) {
                int cnt = 0;
                for (int j = 1; j <= V; j++) {
                    if (naturalDist[i][j] || reverseDist[i][j]) {
                        cnt++;
                    }
                    if (cnt == V) {
                        vertexCnt++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(vertexCnt).append("\n");
        }
        System.out.print(sb);
    }

}
