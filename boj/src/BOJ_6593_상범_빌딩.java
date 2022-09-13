import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593_상범_빌딩 {

    static int H, R, C;
    static char[][][] map;
    static int[][][] dist;
    static Point start, end;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[] dh = {-1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (H == 0 && R == 0 && C == 0) {
                break;
            }

            map = new char[H][R][C];
            dist = new int[H][R][C];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < R; j++) {
                    map[i][j] = br.readLine().toCharArray();
                    for (int k = 0; k < C; k++) {
                        if (map[i][j][k] == 'S') {
                            start = new Point(i, j, k);
                        } else if (map[i][j][k] == 'E') {
                            end = new Point(i, j, k);
                        }
                    }
                }
                br.readLine();
            }

            bfs();
            int answer = dist[end.h][end.r][end.c];

            if (answer != -1) {
                sb.append("Escaped in ").append(answer).append(" minute(s).").append("\n");
            } else {
                sb.append("Trapped!").append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void bfs() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < R; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);
        dist[start.h][start.r][start.c] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int ch = cur.h;
            int cr = cur.r;
            int cc = cur.c;

            for (int h = 0; h < 2; h++) {
                int nh = ch + dh[h];

                if (nh < 0 || nh >= H) {
                    continue;
                }

                if (dist[nh][cr][cc] != -1) {
                    continue;
                }

                if (map[nh][cr][cc] != '#') {
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

                if (map[ch][nr][nc] != '#') {
                    q.offer(new Point(ch, nr, nc));
                    dist[ch][nr][nc] = dist[ch][cr][cc] + 1;
                }
            }
        }
    }


    private static class Point {

        int h, r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }
}
