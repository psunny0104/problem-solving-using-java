import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {

    static int R, C, answer;
    static char[][] map;
    static boolean[][] isSelected;
    static int[] dr = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        R = Integer.parseInt(in.nextToken());
        C = Integer.parseInt(in.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        isSelected = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            isSelected[r][0] = true;
            if (search(r, 0)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean search(int r, int c) {
        if (c == C - 1) {
            return true;
        }

        for (int dir = 0; dir < 3; dir++) {
            int nc = c + 1;
            int nr = r + dr[dir];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                continue;
            }

            if (isSelected[nr][nc]) {
                continue;
            }

            if (map[nr][nc] == 'x') {
                continue;
            }

            isSelected[nr][nc] = true;
            if (search(nr, nc)) {
                return true;
            }
        }
        return false;
    }
}