import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179_불 {

    static int R, C;
    static char[][] map;
    static int[][] fireDist, jDist;
    static ArrayList<Pair> fire = new ArrayList<>();
    static Pair J;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static PriorityQueue<Integer> candidates = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        R = Integer.parseInt(in.nextToken());
        C = Integer.parseInt(in.nextToken());

        map = new char[R][C];
        fireDist = new int[R][C];
        jDist = new int[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'F') {
                    fire.add(new Pair(i, j));
                } else if (map[i][j] == 'J') {
                    J = new Pair(i, j);
                }
            }
        }

        fireSpread();
        moveJ();

        if (candidates.isEmpty()) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(candidates.peek());
        }

    }

    private static void moveJ() {
        if (J.r == R - 1 || J.r == 0 || J.c == C - 1 || J.c == 0) {
            candidates.offer(jDist[J.r][J.c] + 1);
            return;
        }

        Queue<Pair> q = new ArrayDeque<>();
        q.offer(J);
        for (int i = 0; i < R; i++) {
            Arrays.fill(jDist[i], -1);
        }
        jDist[J.r][J.c] = 0;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (jDist[nr][nc] != -1) {
                    continue;
                }

                if (fireDist[nr][nc] != -1 && fireDist[nr][nc] <= jDist[cr][cc] + 1) {
                    continue;
                }

                if (map[nr][nc] == '.') {
                    jDist[nr][nc] = jDist[cr][cc] + 1;
                    q.offer(new Pair(nr, nc));
                    if (nr == R - 1 || nr == 0 || nc == C - 1 || nc == 0) {
                        candidates.offer(jDist[nr][nc] + 1);
                        return;
                    }
                }
            }
        }
    }

    private static void fireSpread() {
        Queue<Pair> q = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            Arrays.fill(fireDist[i], -1);
        }

        for (Pair pair : fire) {
            q.offer(pair);
            fireDist[pair.r][pair.c] = 0;
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (fireDist[nr][nc] != -1) {
                    continue;
                }

                if (map[nr][nc] != '#') {
                    fireDist[nr][nc] = fireDist[cr][cc] + 1;
                    q.offer(new Pair(nr, nc));
                }
            }
        }
    }

    private static class Pair {

        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
