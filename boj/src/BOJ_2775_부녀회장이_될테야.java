import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2775_부녀회장이_될테야 {

    static int T, K, N;
    static int[][] map; // 층, 호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        makeMap();

        for (int tc = 1; tc <= T; tc++) {
            K = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());

            sb.append(map[K][N]).append("\n");
        }
        System.out.print(sb);
    }

    private static void makeMap() {
        map = new int[15][15];
        for (int i = 1; i <= 14; i++) {
            map[0][i] = i;
            map[i][0] = 0;
        }
        for (int i = 1; i <= 14; i++) {
            for (int j = 1; j <= 14; j++) {
                map[i][j] = getValue(i - 1, j);
            }
        }
    }

    private static int getValue(int i, int j) {
        int sum = 0;
        for (int k = 1; k <= j; k++) {
            sum += map[i][k];
        }
        return sum;
    }
}
