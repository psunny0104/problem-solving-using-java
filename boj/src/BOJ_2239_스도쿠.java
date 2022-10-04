import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2239_스도쿠 {

    static int LEN = 9;
    static boolean[][] horizontalCnt;
    static boolean[][] verticalCnt;
    static boolean[][] groupCnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[LEN + 1][LEN + 1];
        horizontalCnt = new boolean[LEN + 1][10];
        verticalCnt = new boolean[LEN + 1][10];
        groupCnt = new boolean[10][10];

        for (int i = 1; i <= LEN; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 1; j <= LEN; j++) {
                map[i][j] = input[j - 1] - '0';
                horizontalCnt[i][map[i][j]] = true;
                verticalCnt[j][map[i][j]] = true;
                groupCnt[((i - 1) / 3) * 3 + ((j - 1) / 3) + 1][map[i][j]] = true;
            }
        }

        if (search(map, 1, 0, false)) {
            System.out.print(sb);
        }

    }

    private static boolean search(int[][] map, int r, int c, boolean isMade) {

        if (r == LEN && c == LEN) {
            for (int i = 1; i <= LEN; i++) {
                for (int j = 1; j <= LEN; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            return true;
        }

        int nc = c == 9 ? 1 : c + 1;
        int nr = c == 9 ? r + 1 : r;

        if (map[nr][nc] != 0) {
            if (search(map, nr, nc, isMade)) {
                return true;
            }
        } else {
            for (int value = 1; value <= 9; value++) {
                if (horizontalCnt[nr][value]) {
                    continue;
                }
                if (verticalCnt[nc][value]) {
                    continue;
                }
                if (groupCnt[((nr - 1) / 3) * 3 + ((nc - 1) / 3) + 1][value]) {
                    continue;
                }
                horizontalCnt[nr][value] = true;
                verticalCnt[nc][value] = true;
                groupCnt[((nr - 1) / 3) * 3 + ((nc - 1) / 3) + 1][value] = true;
                map[nr][nc] = value;
                if (search(map, nr, nc, isMade)) {
                    return true;
                }
                map[nr][nc] = 0;
                horizontalCnt[nr][value] = false;
                verticalCnt[nc][value] = false;
                groupCnt[((nr - 1) / 3) * 3 + ((nc - 1) / 3) + 1][value] = false;
            }
        }
        return isMade;
    }
}
