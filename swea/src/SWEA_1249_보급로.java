import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SWEA_1249_보급로 {

    static int T, N, answer;
    static int[][] map;
    static int[][] depthSum;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 1; j <= N; j++) {
                    map[i][j] = input[j - 1] - '0';
                }
            }

            sb.append("#").append(t).append(" ").append(bfs(1, 1)).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(int r, int c) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(r, c, 0));

        depthSum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(depthSum[i], -1);
        }
        depthSum[r][c] = 0;

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cDepth = cur.depth;

            if (cr == N && cc == N) {
                return cDepth;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 1 || nc < 1 || nr > N || nc > N) {
                    continue;
                }

                if (depthSum[nr][nc] != -1) {
                    continue;
                }

                depthSum[nr][nc] = depthSum[cr][cc] + map[nr][nc];
                pq.offer(new Point(nr, nc, depthSum[nr][nc]));
            }
        }

        return 0;
    }

    private static class Point implements Comparable<Point> {

        int r, c, depth;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }

        @Override
        public int compareTo(Point o) {
            return this.depth - o.depth;
        }
    }
}
