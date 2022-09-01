import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1913_달팽이 {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] map = new int[1000][1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int findNumber = Integer.parseInt(br.readLine());

        int idx = 1;

        int x = n / 2 + 1;
        int y = n / 2 + 1;

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 0);
        }

        map[y][x] = idx++;
        save(n, idx, x, y);
        printMap(n, findNumber, sb);
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    private static void save(int n, int idx, int x, int y) {
        int cy = y;
        int cx = x;
        int range = 1;
        int dir = 0;

        while (idx < n * n) {
            for (int k = 0; k < 2; k++) {
                for (int dist = 0; dist < range; dist++) {
                    int ny = cy + dy[dir];
                    int nx = cx + dx[dir];

                    if (ny < 0 || nx < 0 || ny > n || nx > n) {
                        return;
                    }

                    map[ny][nx] = idx++;

                    cy = ny;
                    cx = nx;
                }
                dir = (dir + 1) % 4;
            }
            range++;
        }
    }

    private static void printMap(int n, int findNumber, StringBuilder sb) {
        int findX = 0;
        int findY = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(map[i][j]).append(" ");
                if (map[i][j] == findNumber) {
                    findY = i;
                    findX = j;
                }
            }
            sb.append("\n");
        }
        sb.append(findY).append(" ").append(findX);
    }
}
