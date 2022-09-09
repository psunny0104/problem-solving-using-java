import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {

    static int R, C, H;
    static int[][][] map, dist;
    static List<Point> tomatos = new ArrayList<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[] dh = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][R][C];
        dist = new int[H][R][C];

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < R; i++) {
                Arrays.fill(dist[h][i], -1);
            }
        }

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[h][i][j] = Integer.parseInt(st.nextToken());
                    if (map[h][i][j] == 1) {
                        tomatos.add(new Point(h, i, j));
                        dist[h][i][j] = 0;
                    } else if (map[h][i][j] == -1) {
                        dist[h][i][j] = -2;
                    }
                }
            }
        }

        if (isThere()) {
            int answer = bfs();
            if (isThere()) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }
        } else {
            System.out.println(0);
        }
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        for (Point tomato : tomatos) {
            q.offer(new Point(tomato.h, tomato.r, tomato.c));
        }

        int phaseCnt = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int ch = cur.h;
            int cr = cur.r;
            int cc = cur.c;

            phaseCnt = Math.max(phaseCnt, dist[ch][cr][cc]);

            for (int h = 0; h < 2; h++) {
                int nh = ch + dh[h];

                if (nh < 0 || nh >= H) {
                    continue;
                }

                if (dist[nh][cr][cc] != -1) {
                    continue;
                }

                if (map[nh][cr][cc] == 0) {
                    q.offer(new Point(nh, cr, cc));
                    dist[nh][cr][cc] = dist[ch][cr][cc] + 1;
                }

            }

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (dist[ch][nr][nc] != -1) {
                    continue;
                }

                if (map[ch][nr][nc] == 0) {
                    q.offer(new Point(ch, nr, nc));
                    dist[ch][nr][nc] = dist[ch][cr][cc] + 1;
                }
            }
        }

        return phaseCnt;
    }

    private static boolean isThere() {
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (dist[h][i][j] == -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Point {

        int h, r, c;

        public Point(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }
}
