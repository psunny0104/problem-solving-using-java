import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_인구_이동 {

    static int N, L, R;
    static int[][] map, dist;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Map<Integer, List<Point>> unions = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            // 연합 만들기
            if (!makeUnion()) {
                break;
            }
            // 인구 이동
            peopleMove();
            day++;
        }
        System.out.println(day);
    }

    private static void peopleMove() {
        int sizes = unions.size();
        for (int i = 1; i <= sizes; i++) {
            int sum = 0;
            List<Point> curPoints = unions.get(i);
            for (Point curPoint : curPoints) {
                sum += map[curPoint.r][curPoint.c];
            }
            sum /= curPoints.size();
            for (Point curPoint : curPoints) {
                map[curPoint.r][curPoint.c] = sum;
            }
        }
    }

    private static boolean makeUnion() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        unions.clear();

        int unionIdx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] != -1) {
                    continue;
                }
                bfs(i, j, unionIdx++);
            }
        }

        if (unions.size() != N * N) {
            return true;
        } else {
            return false;
        }
    }

    private static void bfs(int i, int j, int unionIdx) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(i, j));
        dist[i][j] = unionIdx;
        unions.put(unionIdx, new ArrayList<>());
        unions.get(unionIdx).add(new Point(i, j));

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

                if (dist[nr][nc] != -1) {
                    continue;
                }

                int diff = Math.abs(map[nr][nc] - map[cr][cc]);
                if (L <= diff && diff <= R) {
                    q.offer(new Point(nr, nc));
                    dist[nr][nc] = unionIdx;
                    unions.get(unionIdx).add(new Point(nr, nc));
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
