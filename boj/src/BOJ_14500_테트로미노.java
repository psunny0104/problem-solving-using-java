import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {

    static int R, C, answer = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dr = {0, 1, 0, -1}; // 동, 남, 서, 북
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                isVisited[i][j] = true;
                make(i, j, 1, map[i][j]);
                makeT(i, j, map[i][j]);
                isVisited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static void make(int r, int c, int cnt, int sum) {
        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                continue;
            }
            if (isVisited[nr][nc]) {
                continue;
            }

            isVisited[nr][nc] = true;
            make(nr, nc, cnt + 1, sum + map[nr][nc]);
            isVisited[nr][nc] = false;
        }
    }

    private static void makeT(int r, int c, int sum) {
        for (int t = 0; t < 4; t++) {
            int st = t;
            int ed = (st + 3);
            int makeCnt = 1;
            int phaseSum = sum;
            for (int d = st; d < ed; d++) {
                int nr = r + dr[d % 4];
                int nc = c + dc[d % 4];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    break;
                }
                makeCnt++;
                phaseSum += map[nr][nc];
            }
            if (makeCnt != 4) {
                continue;
            }
            answer = Math.max(answer, phaseSum);
        }
    }
}
