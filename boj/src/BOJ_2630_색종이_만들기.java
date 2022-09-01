import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이_만들기 {

    static int N, whiteCnt, blueCnt;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(in.nextToken());
            }
        }

        dq(0, 0, N);
        System.out.println(whiteCnt + "\n" + blueCnt);
    }

    private static void dq(int stR, int stC, int len) {
        // 길이가 1인 정사각형의 경우 자동 처리
        int checkValue = check(stR, stC, len);
        if (checkValue == 0) {
            whiteCnt++;
        } else if (checkValue == 1) {
            blueCnt++;
        } else {
            len /= 2;

            dq(stR, stC, len);
            dq(stR, stC + len, len);
            dq(stR + len, stC, len);
            dq(stR + len, stC + len, len);
        }
    }

    private static int check(int stR, int stC, int len) { // white:0, blue:1, x: -1
        int tmpWhite = 0;
        int tmpBlue = 0;
        for (int i = stR; i < stR + len; i++) {
            for (int j = stC; j < stC + len; j++) {
                if (map[i][j] == 0) {
                    tmpWhite++;
                } else {
                    tmpBlue++;
                }
            }
        }

        if (tmpBlue != 0 && tmpWhite != 0) {
            return -1;
        }

        return tmpBlue == 0 ? 0 : 1;
    }
}
