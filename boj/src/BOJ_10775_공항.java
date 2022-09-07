import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10775_공항 {

    static int G, P;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        makeSet();

        int cnt = 0;
        boolean isClear = false;
        for (int i = 0; i < P; i++) {
            int target = Integer.parseInt(br.readLine());
            if (isClear) {
                continue;
            }
            int candidate = find(target);
            if (candidate == 0) {
                isClear = true;
                continue;
            }
            union(candidate - 1, candidate);
            cnt++;

        }

        System.out.println(cnt);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }

        p[bRoot] = aRoot;

        return true;
    }

    private static int find(int a) {
        if (a == p[a]) {
            return a;
        }

        return p[a] = find(p[a]);
    }

    private static void makeSet() {
        p = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            p[i] = i;
        }
    }

}