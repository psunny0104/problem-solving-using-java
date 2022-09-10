import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5014_스타트링크 {

    static int F, S, G, U, D;
    static int[] map, dist;
    static int[] dc;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[F + 1];
        dist = new int[F + 1];

        dc = new int[]{U, -D};
        bfs();

        if (dist[G] == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(dist[G]);
        }
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(S);
        Arrays.fill(dist, -1);
        dist[S] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int d = 0; d < 2; d++) {
                int nc = cur + dc[d];

                if (nc < 1 || nc > F) {
                    continue;
                }

                if (dist[nc] != -1) {
                    continue;
                }

                dist[nc] = dist[cur] + 1;
                q.offer(nc);
            }
        }
    }

}
