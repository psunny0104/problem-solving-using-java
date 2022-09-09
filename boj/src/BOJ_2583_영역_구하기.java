import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역_구하기 {

    static int R, C, K;
    static int[][] map;
    static boolean[][] isVisited;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        isVisited = new boolean[R][C];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            for (int j = r1; j < r2; j++) {
                for (int i = c1; i < c2; i++) {
                    map[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1 || isVisited[i][j]) {
                    continue;
                }
                int cnt = bfs(i, j);
                pq.offer(cnt);
            }
        }

        sb.append(pq.size()).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        System.out.print(sb);
    }

    private static int bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        isVisited[r][c] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cc = cur.c;
            int cr = cur.r;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (isVisited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] == 0) {
                    cnt++;
                    q.offer(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                }
            }
        }

        return cnt;
    }


    private static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
