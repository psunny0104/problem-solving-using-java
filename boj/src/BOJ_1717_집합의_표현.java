import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717_집합의_표현 {

    static int N, M;
    static int[] p;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        makeSet();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            operate(type, a, b);
        }
        System.out.print(sb);
    }

    private static void makeSet() {
        p = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            p[i] = i;
        }
    }

    private static void operate(int type, int a, int b) {
        switch (type) {
            case 0:
                unionSet(a, b);
                break;
            case 1:
                determineDisjoint(a, b);
                break;
        }
    }

    private static void determineDisjoint(int a, int b) {
        if (findSet(a) == findSet(b)) {
            sb.append("YES");
        } else {
            sb.append("NO");
        }
        sb.append("\n");
    }

    private static int findSet(int target) {
        if (target == p[target]) {
            return target;
        } else {
            return p[target] = findSet(p[target]);
        }
    }

    private static void unionSet(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return;
        }
        p[aRoot] = bRoot;
    }
}
