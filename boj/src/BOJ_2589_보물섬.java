import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589_보물섬 {

    static int R, C;
    static char[][] map;
    static int[][] dist;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        dist = new int[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'W') {
                    continue;
                }
                for (int k = 0; k < R; k++) {
                    Arrays.fill(dist[k], -1);
                }
                int dist = bfs(i, j);
                answer = Math.max(answer, dist);
            }
        }

        System.out.println(answer);

    }

    private static int bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        dist[r][c] = 0;

        int phaseCnt = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            phaseCnt = Math.max(phaseCnt, dist[cur.r][cur.c]);

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (dist[nr][nc] != -1) {
                    continue;
                }

                if (map[nr][nc] == 'L') {
                    q.offer(new Point(nr, nc));
                    dist[nr][nc] = dist[cur.r][cur.c] + 1;
                }
            }
        }

        return phaseCnt;
    }

    private static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
