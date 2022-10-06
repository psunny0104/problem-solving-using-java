import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색_옷_입은_애가_젤다지 {

    static int N;
    static int[][] map, dist;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            map = new int[N][N];
            dist = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            sb.append("Problem ").append(idx++).append(": ").append(dist[N - 1][N - 1])
                    .append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, map[0][0]));
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cw = cur.weight;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                if (dist[nr][nc] != -1) {
                    continue;
                }

                dist[nr][nc] = cw + map[nr][nc];
                pq.offer(new Point(nr, nc, dist[nr][nc]));
            }
        }

    }

    private static class Point implements Comparable<Point> {

        int r, c, weight;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }
}
