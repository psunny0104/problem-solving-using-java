import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11123_양_한마리_양_두마리 {

    static int T, R, C, lambCnt;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[R + 1][C + 1];
            for (int i = 1; i <= R; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 1; j <= C; j++) {
                    map[i][j] = input[j - 1];
                }
            }

            lambCnt = 0;
            isVisited = new boolean[R + 1][C + 1];
            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    if (isVisited[r][c]) {
                        continue;
                    }
                    if (map[r][c] == '#') {
                        lambCnt++;
                        dfs(r, c);
                    }
                }
            }

            sb.append(lambCnt).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int r, int c) {
        isVisited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 1 || nc < 1 || nr > R || nc > C) {
                continue;
            }

            if (isVisited[nr][nc]) {
                continue;
            }

            if (map[nr][nc] == '#') {
                dfs(nr, nc);
            }
        }
    }
}
