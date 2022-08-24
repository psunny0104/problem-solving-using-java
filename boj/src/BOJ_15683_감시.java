import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {

    // 2^ 16
    // 중복순열
    static int R, C, answer = Integer.MAX_VALUE;
    static int[][] ogMap, cpMap;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
    static int cctvsSize;
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
                    cctvs.add(new CCTV(i, j, ogMap[i][j]));
                }
            }
        }

        cctvsSize = cctvs.size();

        selectedDirs = new int[cctvsSize];

        search(0);
        System.out.println(answer);
    }

    private static void search(int cnt) {
        if (cnt == cctvsSize) {
            // 카메라 타입 세팅
            setCameraType();
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

        for (CCTV cctv : cctvs) {
            switch (cctv.type) {
                case 1:
                    mark(cctv, cctv.dir);
                    break;
                case 2:
                    mark(cctv, cctv.dir);
                    mark(cctv, (cctv.dir + 2) % 4);
                    break;
                case 3:
                    mark(cctv, cctv.dir);
                    mark(cctv, (cctv.dir + 1) % 4);
                    break;
                case 4:
                    mark(cctv, cctv.dir);
                    mark(cctv, (cctv.dir + 1) % 4);
                    mark(cctv, (cctv.dir + 2) % 4);
                    break;
                case 5:
                    mark(cctv, cctv.dir);
                    mark(cctv, (cctv.dir + 1) % 4);
                    mark(cctv, (cctv.dir + 2) % 4);
                    mark(cctv, (cctv.dir + 3) % 4);
                    break;
            }
        }
    }

    private static void mark(CCTV cur, int dir) { // 동, 남, 서, 북
        int nr = cur.r;
        int nc = cur.c;

        int limit = Math.max(R, C);
        for (int k = 0; k < limit; k++) {
            nr += dr[dir];
            nc += dc[dir];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                continue;
            }

            if (cpMap[nr][nc] == 6) {
                break;
            }

            if (cpMap[nr][nc] == 0)
                cpMap[nr][nc] = -1;
        }

    }

    private static void setCameraType() {
        for (int i = 0; i < cctvsSize; i++) {
            cctvs.get(i).dir = selectedDirs[i];
            if (cctvs.get(i).type == 2) {
                cctvs.get(i).dir %= 2;
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
