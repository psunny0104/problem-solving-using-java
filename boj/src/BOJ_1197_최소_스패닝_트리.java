import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197_최소_스패닝_트리 {

    // kruskal
    static int V, E;
    static Edge[] edgeList;
    static int[] p;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        make();
        Arrays.sort(edgeList);

        int weightSum = 0;
        int cnt = 0;

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                weightSum += edge.weight;
                if (++cnt == V - 1) {
                    break;
                }
            }
        }

        System.out.println(weightSum);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        } else if (aRoot < bRoot) {
            p[bRoot] = aRoot;
            return true;
        } else {
            p[aRoot] = bRoot;
            return true;
        }
    }

    private static int find(int a) {
        if (a == p[a]) {
            return a;
        }

        return p[a] = find(p[a]);
    }

    private static void make() {
        p = new int[V + 1];
        for (int i = 0; i < V; i++) {
            p[i] = i;
        }
    }

    private static class Edge implements Comparable<Edge> {

        int from, to, weight;

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
