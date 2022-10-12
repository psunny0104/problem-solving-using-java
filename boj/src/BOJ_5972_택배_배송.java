import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972_택배_배송 {

    static int V, E;
    static Node[] adjList;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new Node[V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node temp = adjList[cur.vertex]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] > dist[cur.vertex] + temp.weight) {
                    dist[temp.vertex] = dist[cur.vertex] + temp.weight;
                    pq.offer(new Node(temp.vertex, dist[temp.vertex]));
                }
            }
        }

        return dist[V];
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
