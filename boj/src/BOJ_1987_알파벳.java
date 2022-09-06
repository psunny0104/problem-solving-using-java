import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {

    static int R, C, answer = Integer.MIN_VALUE;
    static char[][] map;
    static boolean[] isVisited = new boolean[27];
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        search(1, 0, 0);

        System.out.println(answer);
    }

    private static void search(int cnt, int r, int c) {
        if (isVisited[map[r][c] - 'A']) {
            return;
        }

        if (cnt == 27) {
            answer = 26;
            return;
        }

        answer = Math.max(cnt, answer);
        isVisited[map[r][c] - 'A'] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                continue;
            }

            if (isVisited[map[nr][nc] - 'A']) {
                continue;
            }

            search(cnt + 1, nr, nc);
        }
        isVisited[map[r][c] - 'A'] = false;

    }

}
