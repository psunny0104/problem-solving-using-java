import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1245_농장_관리 {

    static int R, C, answer;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dr = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        isVisited = new boolean[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (isVisited[r][c]) {
                    continue;
                }
                bfs(r, c);
            }
        }

        System.out.println(answer);
    }

    private static void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        isVisited[r][c] = true;

        boolean isOkay = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int d = 0; d < 8; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 1 || nc < 1 || nr > R || nc > C) {
                    continue;
                }

                if (map[nr][nc] > map[r][c]) {
                    isOkay = false;
                }

                if (isVisited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] == map[r][c]) {
                    q.offer(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                }
            }

        }

        if (isOkay) {
            answer++;
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
