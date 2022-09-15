import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922_네트워크_연결 {

    static int V, E;
    static Edge[] edgeList;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(from, to, weight);
        }

        makeSet();

        int sum = 0;
        int cnt = 0;
        Arrays.sort(edgeList);

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                sum += edge.weight;
                if (++cnt == V - 1) {
                    break;
                }
            }
        }

        System.out.println(sum);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        } else if (aRoot < bRoot) {
            p[bRoot] = aRoot;
        } else {
            p[aRoot] = bRoot;
        }
        
        return true;
    }

    private static int find(int a) {
        if (a == p[a]) {
            return a;
        }

        return p[a] = find(p[a]);
    }

    private static void makeSet() {
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
