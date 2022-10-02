import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17244_아맞다우산 {

    static int R, C, thingCnt, minDist = Integer.MAX_VALUE;
    static int[] order;
    static char[][] map;
    static int[][] dist;
    static Point start, end;
    static Map<Integer, Point> things = new HashMap<>();
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        dist = new int[R][C];

        int thingIdx = 1;
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    things.put(thingIdx++, new Point(i, j));
                } else if (map[i][j] == 'S') {
                    start = new Point(i, j);
                } else if (map[i][j] == 'E') {
                    end = new Point(i, j);
                }
            }
        }

        thingCnt = things.size();
        if (thingCnt == 0) {
            System.out.println(bfs(start, end));
            return;
        }

        order = new int[thingCnt];
        for (int i = 0; i < thingCnt; i++) {
            order[i] = i + 1;
        }

        do {
            int distSum = 0;
            distSum += bfs(start, things.get(order[0]));
            for (int i = 0; i < thingCnt - 1; i++) {
                distSum += bfs(things.get(order[i]), things.get(order[i + 1]));
            }
            distSum += bfs(things.get(order[thingCnt - 1]), end);
            minDist = Math.min(minDist, distSum);
        } while (np(order));

        System.out.println(minDist);
    }

    private static int bfs(Point st, Point ed) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(st.r, st.c));
        for (int i = 0; i < R; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[st.r][st.c] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            if (cr == ed.r && cc == ed.c) {
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (dist[nr][nc] != -1) {
                    continue;
                }

                if (map[nr][nc] != '#') {
                    dist[nr][nc] = dist[cr][cc] + 1;
                    q.offer(new Point(nr, nc));
                }
            }
        }

        return dist[ed.r][ed.c];
    }

    private static boolean np(int[] input) {
        int N = thingCnt;

        int i = N - 1;
        while (i > 0 && input[i - 1] >= input[i]) {
            --i;
        }

        if (i == 0) {
            return false;
        }

        int j = N - 1;
        while (input[i - 1] >= input[j]) {
            --j;
        }

        swap(input, i - 1, j);

        int k = N - 1;
        while (i < k) {
            swap(input, i++, k--);
        }

        return true;
    }

    private static void swap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    private static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}