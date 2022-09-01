import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// 0, 1
// 1, 0
// 0, -1
// -1, 0
public class SWEA_1954_달팽이_숫자 {

    static int[][] map = new int[11][11];
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int idx = 1;

            int dir = 0;
            int end = n * n;
            int y = 1;
            int x = 1;
            for (int i = 1; i <= n; i++) {
                Arrays.fill(map[i], 0);
            }
            map[y][x] = idx++;

            while (idx <= end) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny < 1 || nx < 1 || ny > n || nx > n || map[ny][nx] != 0) {
                    dir = (dir + 1) % 4;
                }

                y += dy[dir];
                x += dx[dir];
                map[y][x] = idx++;
            }
            print(tc, n);
        }
        System.out.println(sb);
    }

    private static void print(int tc, int n) {
        sb.append("#").append(tc).append("\n");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

}
