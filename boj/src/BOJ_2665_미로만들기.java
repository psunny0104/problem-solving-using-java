import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_2665_미로만들기 {

    static int N, LIMIT = 2501;
    static int[][] map;
    static int[][] cnt;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        bfs(0, 0);
        System.out.println(cnt[N - 1][N - 1]);

    }

    private static void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));

        cnt = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cnt[i], LIMIT);
        }
        cnt[r][c] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                if (cnt[nr][nc] <= cnt[cr][cc]) {
                    continue;
                }

                if (map[nr][nc] == 0) {
                    cnt[nr][nc] = cnt[cr][cc] + 1;
                } else {
                    cnt[nr][nc] = cnt[cr][cc];
                }

                q.offer(new Point(nr, nc));
            }
        }
    }

    private static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
