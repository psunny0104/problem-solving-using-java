import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17070_파이프_옮기기_1 {

    static int N, answer = 0;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        isVisited = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 2, 1);

        System.out.println(answer);
    }

    private static void dfs(int r, int c, int type) {
        if (isVisited[r][c]) {
            return;
        }

        if (r == N && c == N) {
            answer++;
            return;
        }

        isVisited[r][c] = true;

        // 대각선
        int nr = r + 1;
        int nc = c + 1;
        if (!(nr < 1 || nc < 1 || nr > N || nc > N)) {
            if (map[nr][nc] == 0 && map[nr][c] == 0 && map[r][nc] == 0) {
                dfs(nr, nc, 3);
            }
        }

        // 가로
        nr = r;
        nc = c + 1;
        if (!(nr < 1 || nc < 1 || nr > N || nc > N) && type != 2) {
            if (map[nr][nc] == 0) {
                dfs(nr, nc, 1);
            }
        }

        // 세로
        nr = r + 1;
        nc = c;
        if (!(nr < 1 || nc < 1 || nr > N || nc > N) && type != 1) {
            if (map[nr][nc] == 0) {
                dfs(nr, nc, 2);
            }
        }

        isVisited[r][c] = false;
    }

}
