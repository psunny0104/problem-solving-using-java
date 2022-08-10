import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17406_배열_돌리기_4 {

    static int Y, X, R, answer = Integer.MAX_VALUE;
    static int[][] map, ogMap;
    static Operation[] operations;
    static boolean[] selected;
    static int[] selNumbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(in.nextToken());
        X = Integer.parseInt(in.nextToken());
        R = Integer.parseInt(in.nextToken());
        map = new int[Y + 1][X + 1];
        ogMap = new int[Y + 1][X + 1];
        operations = new Operation[R];

        for (int i = 1; i <= Y; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 1; j <= X; j++) {
                map[i][j] = Integer.parseInt(in.nextToken());
                ogMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < R; i++) {
            in = new StringTokenizer(br.readLine());
            operations[i] = new Operation(
                    Integer.parseInt(in.nextToken()),
                    Integer.parseInt(in.nextToken()),
                    Integer.parseInt(in.nextToken())
            );
        }

        selected = new boolean[R];
        selNumbers = new int[R];
        perm(0);

        System.out.println(answer);
    }

    private static void perm(int cnt) {
        if (cnt == R) {
            for (int i = 1; i <= Y; i++) {
                map[i] = Arrays.copyOf(ogMap[i], X + 1);
            }

            for (int i = 0; i < R; i++) {
                Operation cur = operations[selNumbers[i]];
                rotate(cur.stY, cur.stX, cur.edY, cur.edX, cur.lenY, cur.lenX);
//                print();
            }
            calMin();
            return;
        }

        for (int i = 0; i < R; i++) {
            if (selected[i]) {
                continue;
            }

            selected[i] = true;
            selNumbers[cnt] = i;
            perm(cnt + 1);
            selected[i] = false;
        }
    }

    private static void rotate(int stY, int stX, int edY, int edX, int lenY, int lenX) {
        int range = Math.min(lenY, lenX) / 2;
        for (int st = 1; st <= range; st++) {
            int stPoint = map[stY + st - 1][edX - st + 1];
            // 위쪽
            for (int k = edX - st + 1; k >= stX + st; k--) {
                map[stY + st - 1][k] = map[stY + st - 1][k - 1];
            }
            // 왼쪽
            for (int k = stY + st - 1; k <= edY - st; k++) {
                map[k][stX + st - 1] = map[k + 1][stX + st - 1];
            }
            // 아래
            for (int k = stX + st - 1; k <= edX - st; k++) {
                map[edY - st + 1][k] = map[edY - st + 1][k + 1];
            }
            // 오른쪽
            for (int k = edY - st + 1; k >= stY + st; k--) {
                map[k][edX - st + 1] = map[k - 1][edX - st + 1];
            }

            map[stY + st][edX - st + 1] = stPoint;
        }
    }

    private static void calMin() {
        for (int i = 1; i <= Y; i++) {
            answer = Math.min(answer, Arrays.stream(map[i]).sum());
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("================================\n");
        for (int i = 1; i <= Y ; i++) {
            for (int j = 1; j <= X; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("================================\n");
        System.out.println(sb);
    }

    private static class Operation {
        int R;
        int C;
        int S;
        int stY;
        int stX;
        int edY;
        int edX;
        int lenY;
        int lenX;

        public Operation(int r, int c, int s) {
            R = r;
            C = c;
            S = s;
            stY = r - s;
            stX = c - s;
            edY = r + s;
            edX = c + s;
            lenY = edY - stY + 1;
            lenX = edX - stX + 1;
        }
    }
}
