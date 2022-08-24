import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {

    // 중복순열
    static int R, C, answer = Integer.MAX_VALUE;
    static int[][] ogMap, cpMap;
    static ArrayList<CCTV> cameras = new ArrayList<>();
    static int cameraCnt;
    static int[] numbers = {0, 1, 2, 3}, selectedDirs;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ogMap = new int[R][C];
        cpMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                ogMap[i][j] = Integer.parseInt(st.nextToken());
                if (ogMap[i][j] > 0 && ogMap[i][j] != 6) {
                    cameras.add(new CCTV(i, j, ogMap[i][j]));
                }
            }
        }

        cameraCnt = cameras.size();
        selectedDirs = new int[cameraCnt];

        search(0);
        System.out.println(answer);
    }

    private static void search(int cnt) {
        if (cnt == cameraCnt) {
            // 카메라 방향 설정
            setCameraDir();
            // 감시 영역 표현
            markCCTVArea();
            // 사각 지대 확인
            int sum = checkSafeArea();
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            selectedDirs[cnt] = numbers[i];
            search(cnt + 1);
        }
    }

    private static int checkSafeArea() {
        int cnt = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (cpMap[i][j] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void markCCTVArea() {
        for (int i = 0; i < R; i++) {
            cpMap[i] = Arrays.copyOf(ogMap[i], ogMap[i].length);
        }

        for (CCTV cur : cameras) {
            switch (cur.type) {
                case 1:
                    mark(cur.r, cur.c, cur.dir);
                    break;
                case 2:
                    mark(cur.r, cur.c, cur.dir);
                    mark(cur.r, cur.c, (cur.dir + 2) % 4);
                    break;
                case 3:
                    mark(cur.r, cur.c, cur.dir);
                    mark(cur.r, cur.c, (cur.dir + 1) % 4);
                    break;
                case 4:
                    mark(cur.r, cur.c, cur.dir);
                    mark(cur.r, cur.c, (cur.dir + 1) % 4);
                    mark(cur.r, cur.c, (cur.dir + 2) % 4);
                    break;
                case 5:
                    mark(cur.r, cur.c, cur.dir);
                    mark(cur.r, cur.c, (cur.dir + 1) % 4);
                    mark(cur.r, cur.c, (cur.dir + 2) % 4);
                    mark(cur.r, cur.c, (cur.dir + 3) % 4);
                    break;
            }
        }
    }

    private static void mark(int r, int c, int dir) { // 동, 남, 서, 북
        int nr = r;
        int nc = c;

        while (true) {
            nr += dr[dir];
            nc += dc[dir];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                break;
            }

            if (cpMap[nr][nc] == 6) {
                break;
            }

            if (cpMap[nr][nc] == 0)
                cpMap[nr][nc] = -1;
        }

    }

    private static void setCameraDir() {
        for (int i = 0; i < cameraCnt; i++) {
            CCTV cur = cameras.get(i);
            cur.dir = selectedDirs[i];
            if (cur.type == 2) {
                cur.dir %= 2;
            }
        }
    }

    private static class CCTV {
        int r;
        int c;
        int type;
        int dir = 0;

        public CCTV(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }
}
