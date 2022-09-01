import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_2819_격자판의_숫자_이어_붙이기 {

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int T, K = 7, MAX = 4;
    static int[][] map = new int[MAX][MAX];
    static char[] selectedNumbers;
    static HashSet<String> hs = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            for (int i = 0; i < MAX; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 0; j < MAX; j++) {
                    map[i][j] = Integer.parseInt(in.nextToken());
                }
            }

            hs.clear();
            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    selectedNumbers = new char[K];
                    perm(0, i, j);
                }
            }

            sb.append("#").append(tc).append(" ").append(hs.size()).append("\n");
        }
        System.out.print(sb);
    }

    private static void perm(int cnt, int r, int c) {

        if (cnt == K) {
            hs.add(String.valueOf(selectedNumbers));
            return;
        }

        selectedNumbers[cnt] = (char) (map[r][c] + '0');

        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < 0 || nc < 0 || nr >= MAX || nc >= MAX) {
                continue;
            }

            perm(cnt + 1, nr, nc);
        }
    }
}
