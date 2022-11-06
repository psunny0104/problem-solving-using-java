import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2210_숫자판_점프 {


    static int N = 5;
    static int[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static Set<String> answer = new HashSet<>();
    static Deque<String> stk = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                stk.clear();
                stk.offer(String.valueOf(map[r][c]));
                search(0, r, c);
            }
        }

        System.out.println(answer.size());
    }

    private static void search(int cnt, int r, int c) {
        if (cnt == N) {
//            System.out.println(Arrays.toString(stk.toArray()));
            answer.add(Arrays.toString(stk.toArray()));
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 1 || nc < 1 || nr > N || nc > N) {
                continue;
            }

            stk.offerLast(String.valueOf(map[nr][nc]));
            search(cnt + 1, nr, nc);
            stk.pollLast();
        }
    }
}
