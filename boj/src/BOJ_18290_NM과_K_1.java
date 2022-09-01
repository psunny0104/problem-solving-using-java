import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_18290_NM과_K_1 {

    // 100개 중에 4개 -> 판단
    static int R, C, K, size, answer = Integer.MIN_VALUE;
    static Map<Integer, Pair> candidates = new HashMap<>();
    static int[][] map;
    static int[] selectedIndices;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int idx = 1;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                candidates.put(idx++, new Pair(i, j));
            }
        }

        size = candidates.size();
        selectedIndices = new int[K];
        cmb(0, 1);
        System.out.println(answer);
    }

    private static void cmb(int cnt, int start) {
        if (cnt == K) {

            if (isAdj()) {
                return;
            }

            int sum = 0;
            for (int selectedIndex : selectedIndices) {
                Pair cur = candidates.get(selectedIndex);
                sum += map[cur.r][cur.c];
            }

            answer = Math.max(answer, sum);
            return;
        }

        for (int i = start; i <= size; i++) {
            selectedIndices[cnt] = i;
            cmb(cnt + 1, i + 1);
        }
    }

    private static boolean isAdj() {

        for (int selectedIndex : selectedIndices) {
            for (int selectedIndexTwo : selectedIndices) {
                if (selectedIndex == selectedIndexTwo) {
                    continue;
                }

                Pair cur = candidates.get(selectedIndex);
                Pair target = candidates.get(selectedIndexTwo);
                
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr == target.r && nc == target.c) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Pair {

        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
