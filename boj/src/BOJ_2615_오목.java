import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목 {

    static int N = 19;
    static int[][] map;
    static Point[] madeInfo;

    static int[] dr = {0, 1, 1, 1};
    static int[] dc = {1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        madeInfo = new Point[3];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                int result = check(i, j, map[i][j]);
                if (result != -1) {
                    if (result == 3) {
                        madeInfo[map[i][j]] = new Point(i + 4, j - 4, map[i][j]);
                    } else {
                        madeInfo[map[i][j]] = new Point(i, j, map[i][j]);
                    }
                }
            }
        }

        if (madeInfo[1] == null && madeInfo[2] == null) {
            System.out.println(0);
        } else if (madeInfo[1] != null) {
            System.out.println(1);
            System.out.println(madeInfo[1].r + " " + madeInfo[1].c);
        } else {
            System.out.println(2);
            System.out.println(madeInfo[2].r + " " + madeInfo[2].c);
        }
    }

    private static int check(int r, int c, int color) {
        int method = -1;

        for (int d = 0; d < 4; d++) {
            int nr = r;
            int nc = c;
            int cnt = 1;
            while (true) {
                nr += dr[d];
                nc += dc[d];

                if (nr < 1 || nc < 1 || nr > N || nc > N) {
                    break;
                }

                if (color == map[nr][nc]) {
                    cnt++;
                } else {
                    break;
                }
            }
            if (cnt == 5) {
                if (d == 0) {
                    if (c - 1 >= 1 && map[r][c - 1] == color) {
                        continue;
                    }
                } else if (d == 1) {
                    if (r - 1 >= 1 && map[r - 1][c] == color) {
                        continue;
                    }
                } else if (d == 2) {
                    if (c - 1 >= 1 && r - 1 >= 1 && map[r - 1][c - 1] == color) {
                        continue;
                    }
                } else {
                    if (c + 1 <= N && r - 1 >= 1 && map[r - 1][c + 1] == color) {
                        continue;
                    }
                }
                method = d;
                break;
            }
        }
        return method;
    }

    private static class Point {

        int r, c, color;

        public Point(int r, int c, int color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
}
