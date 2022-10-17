import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1189_컴백홈 {

    static int R, C, K, answer;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        isVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        isVisited[R - 1][0] = true;
        search(1, R - 1, 0);

        System.out.println(answer);
    }

    private static void search(int cnt, int r, int c) {
        if (cnt == K) {
            if (r == 0 && c == C - 1) {
                answer++;
            }
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

            if (map[nr][nc] == 'T') {
                continue;
            }

            isVisited[nr][nc] = true;
            search(cnt + 1, nr, nc);
            isVisited[nr][nc] = false;
        }
    }

}
