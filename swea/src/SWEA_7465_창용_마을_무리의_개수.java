import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_7465_창용_마을_무리의_개수 {

    static int T, N, M;
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            makeSet();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                union(from, to);
            }

            int cnt = (int) Arrays.stream(p).filter(value -> value < 0).count();

            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }

        p[aRoot] += p[bRoot];
        p[bRoot] = aRoot;

        return true;
    }

    private static int find(int a) {
        if (p[a] < 0) {
            return a;
        }

        return p[a] = find(p[a]);
    }

    private static void makeSet() {
        p = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = -1;
        }
    }
}
