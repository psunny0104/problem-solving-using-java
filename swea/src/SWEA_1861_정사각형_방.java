import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형_방 {

    static int N, idx, cnt;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            idx = Integer.MAX_VALUE;
            cnt = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(in.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    isVisited = new boolean[N][N];
                    find(i, j, i, j, map[i][j], 1);
                }
            }

            sb.append("#").append(tc).append(" ").append(idx).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    private static void find(int stY, int stX, int y, int x, int curIdx, int curCnt) {
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};
        isVisited[y][x] = true;

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                continue;
            }
            if (isVisited[ny][nx]) {
                continue;
            }
            if (map[ny][nx] != map[y][x] + 1) {
                continue;
            }

            if (curCnt + 1 > cnt) {
                cnt = curCnt + 1;
                idx = map[stY][stX];
            } else if (curCnt + 1 == cnt) {
                idx = Math.min(idx, map[stY][stX]);
            }

            isVisited[ny][nx] = true;
            find(stY, stX, ny, nx, map[ny][nx], curCnt + 1);
        }
    }
}
