import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_벽_부수고_이동하기 {

    static int R, C, answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[][][] dist;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        dist = new int[R][C][2];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().chars().map(e -> e - 48).toArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }

        answer = bfs();

        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));
        dist[0][0][0] = dist[0][0][1] = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cwallUsed = cur.wallUsed;

            if (cr == R - 1 && cc == C - 1) {
                return dist[cr][cc][cwallUsed];
            }

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (map[nr][nc] == 0 && dist[nr][nc][cwallUsed] == -1) {
                    dist[nr][nc][cwallUsed] = dist[cr][cc][cwallUsed] + 1;
                    q.offer(new Point(nr, nc, cwallUsed));
                }

                if (cwallUsed == 0 && map[nr][nc] == 1 && dist[nr][nc][1] == -1) {
                    dist[nr][nc][1] = dist[cr][cc][cwallUsed] + 1;
                    q.offer(new Point(nr, nc, 1));
                }
            }
        }

        return -1;
    }

    private static class Point {

        int r, c;
        int wallUsed = 0;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int wallUsed) {
            this.r = r;
            this.c = c;
            this.wallUsed = wallUsed;
        }
    }
}
