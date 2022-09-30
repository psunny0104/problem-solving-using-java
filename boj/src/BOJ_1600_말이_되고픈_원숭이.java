import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이_되고픈_원숭이 {

    static int R, C, K;
    static int[][] map;
    static boolean[][][] isVisited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[] dr8 = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dc8 = {1, -1, -2, 2, -2, 2, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(1, 1));
    }

    private static int bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        isVisited = new boolean[R + 1][C + 1][K + 1];
        isVisited[1][1][0] = true; // k를 0번 사용하여 r,c에 도달

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            int ck = cur.k;
            int cDist = cur.dist;

            if (cr == R && cc == C) {
                return cur.dist;
            }

            // 4방
            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 1 || nc < 1 || nr > R || nc > C) {
                    continue;
                }

                if (isVisited[nr][nc][ck]) {
                    continue;
                }

                if (map[nr][nc] == 0) {
                    q.offer(new Point(nr, nc,cDist+1, ck));
                    isVisited[nr][nc][ck] = true;
                }
            }
            // 말
            if (ck < K) {
                for (int d = 0; d < 8; d++) {
                    int nr = cr + dr8[d];
                    int nc = cc + dc8[d];

                    if (nr < 1 || nc < 1 || nr > R || nc > C) {
                        continue;
                    }

                    if (isVisited[nr][nc][ck+1]) {
                        continue;
                    }

                    if (map[nr][nc] == 0) {
                        q.offer(new Point(nr, nc,  cDist+1, ck+1));
                        isVisited[nr][nc][ck + 1] = true;
                    }
                }
            }
        }
        return -1;
    }

    private static class Point {

        int r, c, dist, k;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
            this.k = 0;
            this.dist = 0;
        }

        public Point(int r, int c, int dist, int k) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.k = k;
        }
    }
}
