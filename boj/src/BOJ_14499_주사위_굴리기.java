import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위_굴리기 {

    static int R, C, K;
    static Dice dice;
    static int[][] map;
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0}; // 동으로, 서, 북, 남
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dice = new Dice(0, 0);
        dice.r = Integer.parseInt(st.nextToken());
        dice.c = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int order = Integer.parseInt(st.nextToken());
            if (rotate(order)) {
                sb.append(dice.up).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static boolean rotate(int order) {
        int nr = dice.r + dr[order];
        int nc = dice.c + dc[order];
        if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
            return false;
        }

        int temp;
        switch (order) {
            case 1: // 동
                temp = dice.right;
                dice.right = dice.up;
                dice.up = dice.left;
                dice.left = dice.down;
                dice.down = temp;
                break;
            case 2: // 서
                temp = dice.right;
                dice.right = dice.down;
                dice.down = dice.left;
                dice.left = dice.up;
                dice.up = temp;
                break;
            case 3: // 북
                temp = dice.up;
                dice.up = dice.front;
                dice.front = dice.down;
                dice.down = dice.back;
                dice.back = temp;
                break;
            case 4: // 남
                temp = dice.up;
                dice.up = dice.back;
                dice.back = dice.down;
                dice.down = dice.front;
                dice.front = temp;
                break;
        }

        dice.r = nr;
        dice.c = nc;
        swap();

        return true;
    }

    private static void swap() {
        if (map[dice.r][dice.c] == 0) {
            map[dice.r][dice.c] = dice.down;
        } else {
            dice.down = map[dice.r][dice.c];
            map[dice.r][dice.c] = 0;
        }
    }

    private static class Dice {
        int r, c;

        int up = 0;
        int down = 0;
        int right = 0;
        int left = 0;
        int front = 0;
        int back = 0;

        public Dice(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
