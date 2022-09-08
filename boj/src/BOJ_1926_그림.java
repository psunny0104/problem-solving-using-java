import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1926_그림 {

    static int R, C;
    static int[][] map, dist;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        dist = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < R; i++) {
            Arrays.fill(dist[i], -1);
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0 || dist[i][j] != -1) {
                    continue;
                }
                int area = bfs(i, j);
                pq.offer(area);
            }
        }

        int maxArea = pq.size() == 0 ? 0 : pq.peek();
        System.out.println(pq.size() + "\n" + maxArea);

    }

    private static int bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        int cnt = 1;
        dist[r][c] = cnt;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (dist[nr][nc] != -1) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    q.offer(new Point(nr, nc));
                    dist[nr][nc] = ++cnt;
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
