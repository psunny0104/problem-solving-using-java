import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {

    static int R, C;
    static int[][] ogMap, cpMap;
    static boolean[][] isVisited;
    static Deque<Integer> meltedCheeseCnt = new ArrayDeque<>();
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ogMap = new int[R + 2][C + 2];
        cpMap = new int[R + 2][C + 2];
        for (int i = 1; i <=R ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                ogMap[i][j] = Integer.parseInt(st.nextToken());
                cpMap[i][j] = ogMap[i][j];
            }
        }

        int time = 0;
        isVisited = new boolean[R + 2][C + 2];
        while (true) {
            for (int i = 0; i <= R; i++) {
                Arrays.fill(isVisited[i], false);
            }
            bfs(0, 0);
            copy();
            time++;
            if (isEveryCheeseMelted()) {
                break;
            }
        }

        System.out.println(time);
        System.out.println(meltedCheeseCnt.pollLast());
    }

    private static boolean isEveryCheeseMelted() {
        for (int i = 1; i <=R; i++) {
            for (int j = 1; j <= C ; j++) {
                if (ogMap[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void copy() {
        for (int i = 1; i <=R; i++) {
            for (int j = 1; j <= C ; j++) {
                ogMap[i][j] = cpMap[i][j];
            }
        }
    }

    private static void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        isVisited[r][c] = true;

        int curMeltedCheeseCnt = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr > R+1 || nc > C+1) {
                    continue;
                }

                if (isVisited[nr][nc]) {
                    continue;
                }

                isVisited[nr][nc] = true;
                if (ogMap[nr][nc] == 0) {
                    q.offer(new Point(nr, nc));
                } else if (ogMap[nr][nc] == 1) {
                    cpMap[nr][nc] = 0;
                    curMeltedCheeseCnt++;
                }
            }
        }
        meltedCheeseCnt.offer(curMeltedCheeseCnt);
    }

    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
