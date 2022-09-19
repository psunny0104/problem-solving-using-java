import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지_안녕 {

    static int R, C, T;
    static int[][] ogMap, cpMap;
    static List<Point> cleaners = new ArrayList<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        ogMap = new int[R + 1][C + 1];
        cpMap = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                ogMap[i][j] = Integer.parseInt(st.nextToken());
                if (ogMap[i][j] == -1) {
                    cleaners.add(new Point(i, j));
                }
            }
        }

        while (T-- > 0) {
            spreadDust();
            operateCleaner();
        }

        System.out.println(check());
    }

    private static int check() {
        int cnt = 0;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                cnt += ogMap[i][j];
            }
        }
        return cnt + 2;
    }

    private static void operateCleaner() {
        // 위쪽
        int uStartR = cleaners.get(0).r;
        int uStartC = 1;

        for (int r = uStartR - 1; r > 1; r--) {
            ogMap[r][1] = ogMap[r - 1][1];
        }

        for (int c = 1; c < C; c++) {
            ogMap[1][c] = ogMap[1][c + 1];
        }

        for (int r = 1; r < uStartR; r++) {
            ogMap[r][C] = ogMap[r + 1][C];
        }

        for (int c = C; c > uStartC + 1; c--) {
            ogMap[uStartR][c] = ogMap[uStartR][c - 1];
        }
        ogMap[uStartR][2] = 0;

        // 아래쪽
        int dStartR = cleaners.get(1).r;
        int dStartC = 1;

        for (int r = dStartR + 1; r < R; r++) {
            ogMap[r][1] = ogMap[r + 1][1];
        }

        for (int c = 1; c < C; c++) {
            ogMap[R][c] = ogMap[R][c + 1];
        }

        for (int r = R; r > dStartR; r--) {
            ogMap[r][C] = ogMap[r - 1][C];
        }

        for (int c = C; c > dStartC + 1; c--) {
            ogMap[dStartR][c] = ogMap[dStartR][c - 1];
        }
        ogMap[dStartR][2] = 0;
    }

    private static void spreadDust() {
        copyMap();
        for (Point cleaner : cleaners) {
            ogMap[cleaner.r][cleaner.c] = -1;
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (cpMap[r][c] <= 0) {
                    continue;
                }

                int dirCnt = 0;
                int dustAmount = cpMap[r][c] / 5;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 1 || nc < 1 || nr > R || nc > C) {
                        continue;
                    }

                    if (cpMap[nr][nc] == -1) {
                        continue;
                    }

                    dirCnt++;
                    ogMap[nr][nc] += dustAmount;
                }
                ogMap[r][c] += cpMap[r][c] - ((dustAmount) * dirCnt);
            }
        }
    }

    private static void copyMap() {
        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= C; j++) {
                cpMap[i][j] = ogMap[i][j];
                ogMap[i][j] = 0;
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
