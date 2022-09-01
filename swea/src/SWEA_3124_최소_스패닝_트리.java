import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3124_최소_스패닝_트리 {

    static int T, V, E;
    static Edge[] edgeList;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edgeList = new Edge[E];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }

            make();
            Arrays.sort(edgeList);

            long minWeightSum = 0;
            int cnt = 0;

            for (Edge edge : edgeList) {
                if (union(edge.from, edge.to)) {
                    minWeightSum += edge.weight;
                    if (++cnt == V - 1) {
                        break;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(minWeightSum).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean union(int from, int to) {
        int a = find(from);
        int b = find(to);

        if (a == b) {
            return false;
        }

        p[a] = b;
        return true;
    }

    private static int find(int a) {
        if (a == p[a]) {
            return a;
        } else {
            return p[a] = find(p[a]);
        }
    }

    private static void make() {
        p = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            p[i] = i;
        }
    }

    private static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
