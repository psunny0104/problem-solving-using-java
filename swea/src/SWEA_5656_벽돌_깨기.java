import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌_깨기 {

    // 중복 순열
    static int T, N, R, C, maxCnt;
    static int[][] map, ogMap;
    static boolean[][] isVisited;
    static int[] selectedNumber;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            map = new int[R + 1][C + 1];
            ogMap = new int[R + 1][C + 1];
            for (int i = 1; i <= R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    ogMap[i][j] = map[i][j];
                }
            }

            maxCnt = Integer.MAX_VALUE;
            selectedNumber = new int[N];
            dupPerm(0);

            sb.append("#").append(tc).append(" ").append(maxCnt).append("\n");
        }
        System.out.print(sb);
    }

    private static void dupPerm(int cnt) {
        if (cnt == N) {
            for (int curWidth : selectedNumber) {
                isVisited = new boolean[R + 1][C + 1];
                dropBlock(curWidth);
                moveBlocks();
            }

            int curCnt = cntBlock();
            maxCnt = Math.min(maxCnt, curCnt);
            copy();

            return;
        }

        for (int i = 1; i <= C; i++) {
            selectedNumber[cnt] = i;
            dupPerm(cnt + 1);
        }
    }

    private static void copy() {
        for (int i = 1; i <= R; i++) {
            for (int j = 0; j <= C; j++) {
                map[i][j] = ogMap[i][j];
            }
        }
    }

    private static void moveBlocks() {
        for (int c = 1; c <= C; c++) {
            int r = R;
            while (r >= 1) {
                if (map[r][c] > 0) {
                    int dest = 0;
                    for (int i = r; i <= R; i++) {
                        if (map[i][c] == 0) {
                            dest = i;
                        }
                    }
                    if (dest != 0) {
                        map[dest][c] = map[r][c];
                        map[r][c] = 0;
                    }
                }
                r--;
            }
        }
    }

    private static void dropBlock(int curWidth) {
        int targetRow = 0;
        for (int row = 1; row <= R; row++) {
            if (map[row][curWidth] != 0) {
                targetRow = row;
                break;
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(targetRow, curWidth, map[targetRow][curWidth]));
        isVisited[targetRow][curWidth] = true;
        map[targetRow][curWidth] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cRange = cur.range;

            for (int len = 1; len <= cRange - 1; len++) {
                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d] * (len);
                    int nc = cc + dc[d] * (len);

                    if (nr < 1 || nc < 1 || nr > R || nc > C) {
                        continue;
                    }

                    if (isVisited[nr][nc]) {
                        continue;
                    }

                    if (map[nr][nc] > 0) {
                        if (map[nr][nc] > 1) {
                            q.add(new Point(nr, nc, map[nr][nc]));
                        }
                        isVisited[nr][nc] = true;
                        map[nr][nc] = 0;
                    }
                }
            }
        }


    }

    private static int cntBlock() {
        int cnt = 0;

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] != 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static class Point {

        int r, c, range;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int range) {
            this.r = r;
            this.c = c;
            this.range = range;
        }
    }
}
