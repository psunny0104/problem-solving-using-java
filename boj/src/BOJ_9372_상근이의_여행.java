import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9372_상근이의_여행 {

    static int T, N, M;
    static int[] p;
    static Edge[] edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            edgeList = new Edge[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to);
            }

//            make();
//
//            int count = 0;
//            int answer = 0;
//            for (Edge edge : edgeList) {
//                if (union(edge.from, edge.to)) {
//                    answer += 1;
//                    if (++count == N-1) break;
//                }
//            }

            int answer = N - 1;
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }

        p[bRoot] += p[aRoot];
        p[aRoot] = bRoot;
        return true;
    }

    private static int find(int a) {
        if (p[a] < 0) {
            return a;
        } else {
            return p[a] = find(p[a]);
        }
    }

    private static void make() {
        p = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = -1;
        }
    }

    private static class Edge {

        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

}
