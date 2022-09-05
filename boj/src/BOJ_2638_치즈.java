import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_치즈 {

    static int R, C;
    static int[][] map, externalDirCnt;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        externalDirCnt = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hour = 0;
        while (true) {
            if (!bfs()) {
                break;
            }
            remove();
            hour++;
        }

        System.out.println(hour);
    }

    private static void remove() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (externalDirCnt[i][j] >= 2) {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static boolean bfs() {
        boolean doCnt = false;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));
        for (int i = 0; i < R; i++) {
            Arrays.fill(externalDirCnt[i], 0);
        }
        externalDirCnt[0][0] = -1;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (externalDirCnt[nr][nc] == -1) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    externalDirCnt[nr][nc] += 1;
                    doCnt = true;
                } else if (map[nr][nc] == 0) {
                    q.offer(new Point(nr, nc));
                    externalDirCnt[nr][nc] = -1;
                }
            }
        }

        return doCnt;
    }

    private static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
