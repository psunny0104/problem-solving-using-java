import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926_배열_돌리기_1 {

    static int Y, X, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(in.nextToken());
        X = Integer.parseInt(in.nextToken());
        R = Integer.parseInt(in.nextToken());
//        R = R % (Y * 2 + (X - 2) * 2); //0 == 14

        map = new int[Y + 1][X + 1];

        for (int i = 1; i <= Y; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 1; j <= X; j++) {
                map[i][j] = Integer.parseInt(in.nextToken());
            }
        }

        while (R > 0) {
            map = rotate();
            R--;
        }

        print();
    }

    private static int[][] rotate() {
        int[][] newMap = new int[Y + 1][X + 1];
        int range = Math.min(Y, X) / 2;
        for (int st = 1; st <= range; st++) {
            // 위쪽
            for (int k = st; k <= X - st; k++) {
                newMap[st][k] = map[st][k + 1];
            }
            // 아래
            for (int k = X - st + 1; k >= st + 1; k--) {
                newMap[Y - st + 1][k] = map[Y - st + 1][k - 1];
            }
            // 오른쪽
            for (int k = st; k <= Y - st; k++) {
                newMap[k][X - st + 1] = map[k + 1][X - st + 1];
            }

            // 왼쪽
            for (int k = Y - st + 1; k >= st + 1; k--) {
                newMap[k][st] = map[k - 1][st];
            }
        }

        return newMap;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= Y; i++) {
            for (int j = 1; j <= X; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
