import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4991_로봇_청소기 {

    static int R, C, thingCnt, minDist;
    static int[] order;
    static char[][] map;
    static int[][] dist, distAdjMatrix;
    static Map<Integer, Point> things = new HashMap<>();
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            if (R == 0 && C == 0) {
                System.out.print(sb);
                break;
            }

            map = new char[R][C];
            dist = new int[R][C];
            things.clear();

            int thingIdx = 1;
            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '*') {
                        things.put(thingIdx++, new Point(i, j));
                    } else if (map[i][j] == 'o') {
                        things.put((0), new Point(i, j));
                    }
                }
            }

            thingCnt = things.size();
            if (thingCnt == 0) {
                sb.append(0);
                continue;
            }

            order = new int[thingCnt - 1];
            for (int i = 0; i < thingCnt - 1; i++) {
                order[i] = i + 1;
            }

            makeDistAdjMatrix();

            minDist = Integer.MAX_VALUE;
            do {
                int distSum = 0;
                int sum = distAdjMatrix[0][order[0]];
                if (sum == -1) {
                    minDist = -1;
                    break;
                }
                distSum += sum;
                for (int i = 0; i < thingCnt - 2; i++) {
                    sum = distAdjMatrix[order[i]][order[i + 1]];
                    if (sum == -1) {
                        minDist = -1;
                        break;
                    }
                    distSum += sum;
                }

                minDist = Math.min(minDist, distSum);
            } while (np(order));

            sb.append(minDist).append("\n");
        }

    }

    private static void makeDistAdjMatrix() {
        distAdjMatrix = new int[thingCnt + 1][thingCnt + 1];
        for (int i = 0; i < thingCnt; i++) {
            for (int j = 0; j < thingCnt; j++) {
                if (distAdjMatrix[j][i] != 0) {
                    distAdjMatrix[i][j] = distAdjMatrix[j][i];
                } else {
                    bfs(things.get(i));
                    distAdjMatrix[i][j] = dist[things.get(j).r][things.get(j).c];
                }
            }
        }
    }

    private static void bfs(Point st) {
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

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (dist[nr][nc] != -1) {
                    continue;
                }

                if (map[nr][nc] != 'x') {
                    dist[nr][nc] = dist[cr][cc] + 1;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static boolean np(int[] input) {
        int N = input.length;

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