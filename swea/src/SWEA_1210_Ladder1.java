import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder1 {

    static int[] dy = {0, 0, -1}; // 오른, 왼, 위
    static int[] dx = {+1, -1, 0};
    static int LENGTH = 100;
    static int[][] ladder = new int[LENGTH][LENGTH];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());

            int stX = 0;
            int stY = 99;

            for (int i = 0; i < LENGTH; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 0; j < LENGTH; j++) {
                    ladder[i][j] = Integer.parseInt(in.nextToken());
                    if (ladder[i][j] == 2) {
                        stX = j;
                    }
                }
            }

            int answer = dfs(stY, stX);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int stY, int stX) {
        int destX = 0;

        boolean[][] chk = new boolean[100][100];
        for (int i = 0; i < LENGTH; i++) {
            Arrays.fill(chk[i], false);
        }
        chk[stY][stX] = true;

        Stack<Pair> stk = new Stack<>();
        stk.add(new Pair(stY, stX));
        boolean isFind = false;

        while (!stk.isEmpty()) {
            Pair cur = stk.pop();
            int cy = cur.y;
            int cx = cur.x;

            for (int k = 0; k < 3; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];

                if (ny < 0 || nx < 0 || ny >= 100 || nx >= 100) {
                    continue;
                }

                if (ladder[ny][nx] != 1) {
                    continue;
                }

                if (chk[ny][nx] == true) {
                    continue;
                }

                chk[ny][nx] = true;
                stk.add(new Pair(ny, nx));

                if (k == 0 || k == 1) {
                    break;
                }

                if (ny == 0) {
                    destX = nx;
                    isFind = true;
                    break;
                }
            }
            if (isFind) {
                break;
            }
        }
        return destX;
    }

    private static class Pair {

        private int y;
        private int x;

        public Pair(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
}
