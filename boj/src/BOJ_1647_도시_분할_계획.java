import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시_분할_계획 {

    static int V, E;
    static int[] p;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(from, to, weight));
        }

        makeSet();

        int sum = 0;
        int cnt = 0;
        int maxWeight = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union(edge.from, edge.to)) {
                sum += edge.weight;
                maxWeight = Math.max(maxWeight, edge.weight);
                if (++cnt == V - 1) {
                    break;
                }
            }
        }

        System.out.println(sum - maxWeight);
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
        p = new int[V + 1];
        for (int i = 0; i <= V; i++) {
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
