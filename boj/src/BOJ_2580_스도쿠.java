import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2580_스도쿠 {

    static int N = 9;
    static int[][] map;
    static int[][] vertical;
    static int[][] horizontal;
    static int[][] group;
    static Map<Integer, Point> candidates = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        map = new int[N + 1][N + 1];
        vertical = new int[N + 1][N + 1];
        horizontal = new int[N + 1][N + 1];
        group = new int[N + 1][N + 1];

        int idx = 1;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                horizontal[i][map[i][j]]++;
                vertical[j][map[i][j]]++;
                group[((i - 1) / 3) * 3 + ((j - 1) / 3) + 1][map[i][j]]++;
                candidates.put(idx, new Point(idx++, i, j));
            }
        }

        if (search(1)) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean search(int cnt) {
        if (cnt == 82) {
            if (isOkay()) {
                return true;
            }
            return false;
        }

        int r = candidates.get(cnt).r;
        int c = candidates.get(cnt).c;
        if (map[r][c] != 0) {
            if (search(cnt + 1)) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (vertical[c][i] > 0) {
                continue;
            }

            if (horizontal[r][i] > 0) {
                continue;
            }

            if (group[((r - 1) / 3) * 3 + ((c - 1) / 3) + 1][i] > 0) {
                continue;
            }

            map[r][c] = i;
            vertical[c][i]++;
            horizontal[r][i]++;
            group[((r - 1) / 3) * 3 + ((c - 1) / 3) + 1][i]++;
            if (search(cnt + 1)) {
                return true;
            }
            map[r][c] = 0;
            vertical[c][i]--;
            horizontal[r][i]--;
            group[((r - 1) / 3) * 3 + ((c - 1) / 3) + 1][i]--;
        }

        return false;
    }

    private static boolean isOkay() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (vertical[i][j] <= 0) {
                    return false;
                }
                if (horizontal[i][j] <= 0) {
                    return false;
                }
                if (group[i][j] <= 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static class Point {

        int idx;
        int r, c;

        public Point(int idx, int r, int c) {
            this.idx = idx;
            this.r = r;
            this.c = c;
        }
    }
}
