import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_여행_가자 {

    static int n, m;
    static int[] p, candidates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        makeSet();
        candidates = new int[m+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            candidates[i] = Integer.parseInt(st.nextToken());
        }


        if (check()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean check() {
        int target = p[candidates[1]];
        for (int i = 2; i <= m; i++) {
            if (target != find(candidates[i])) {
                return false;
            }
        }

        return true;
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            p[bRoot] = aRoot;
        }
    }

    private static int find(int target) {
        if (target == p[target]) {
            return target;
        } else {
            return p[target] = find(p[target]);
        }
    }

    private static void makeSet() {
        p = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            p[i] = i;
        }
    }
}
