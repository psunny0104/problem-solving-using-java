import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14716_현수막 {

    static int R, C, answer;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dr = {0, 0, 1, 1, 1, -1, -1, -1};
    static int[] dc = {1, -1, 0, 1, -1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (isVisited[i][j]) {
                    continue;
                }
                if (map[i][j] == 1) {
                    bfs(i, j);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        isVisited[r][c] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int d = 0; d < 8; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (isVisited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    q.offer(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                }
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
