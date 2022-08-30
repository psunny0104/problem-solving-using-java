import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇_청소기 {

    static int R, C, cnt;
    static int[][] map;
    static int[][] isVisited;
    static Robot rb;
    static int[] dr = {0, -1, 0, 1}; // 서, 북, 동, 남
    static int[] dc = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        rb = new Robot(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        map = new int[R][C];
        isVisited = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    isVisited[i][j] = 2;
                }
            }
        }

        while (true) {
            cleanCurLocation();
            int rotateCnt = 0;
            for (int dir = 0; dir < 4; dir++) {
                if (rotate()) {
                    rotateCnt++;
                } else {
                    move();
                    break;
                }
            }
            if (rotateCnt == 4) {
                int backMoveCnt = 0;
                for (int i = 0; i < 4; i++) {
                    if (backMove()) {
                        backMoveCnt++;
                    } else {
                        break;
                    }
                }
                if (backMoveCnt == 4) {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }

    private static boolean backMove() {
        int nr = rb.r + dr[(rb.dir + 3) % 4];
        int nc = rb.c + dc[(rb.dir + 3) % 4];

        if (map[nr][nc] == 1) {
            return true;
        }
        if ( nr == 0 || nc == 0 || nr >= R-1 || nc >= C-1) {
            rb.dir = (rb.dir + 3) % 4;
            return true;
        }

        rb.r += dr[(rb.dir + 3) % 4];
        rb.c += dc[(rb.dir + 3) % 4];
        return false;
    }

    private static void move() {
        rb.r += dr[rb.dir];
        rb.c += dc[rb.dir];
//        isVisited[rb.r][rb.c] = 1;
        rb.dir = (rb.dir + 3) % 4;
    }

    private static boolean rotate() {
        int nr = rb.r + dr[rb.dir];
        int nc = rb.c + dc[rb.dir];

        if (nr <= 0 || nc <= 0 || nr >= R-1 || nc >= C-1) {
            rb.dir = (rb.dir + 3) % 4;
            return true;
        }
        if (isVisited[nr][nc] == 1 || map[nr][nc] == 1) {
            rb.dir = (rb.dir + 3) % 4;
            return true;
        }

        return false;
    }

    private static void cleanCurLocation() {
        if (isVisited[rb.r][rb.c] == 0) {
            isVisited[rb.r][rb.c] = 1;
            cnt++;
        }
    }

    private static class Robot {
        int r, c, dir;

        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}