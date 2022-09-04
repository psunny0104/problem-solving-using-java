import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용_구하기 {

    static int V, E, start, end;
    static Node[] adjList;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        adjList = new Node[V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra();

        System.out.println(dist[end]);

    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node temp = adjList[cur.vertex]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] > dist[cur.vertex] + temp.weight) {
                    dist[temp.vertex] = dist[cur.vertex] + temp.weight;
                    pq.offer(new Node(temp.vertex, dist[temp.vertex]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {

        int vertex;
        int weight;
        Node next;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
