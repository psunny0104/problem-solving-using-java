import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {

    static int V, E, start;
    static Node[] adjList;
    static int[] dist;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        adjList = new Node[V+1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
        }

        dijkstra();

        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }
        System.out.print(sb);

    }

    private static void dijkstra() {
        dist = new int[V+1];
        isVisited = new boolean[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curVertex = cur.value;

            if (isVisited[curVertex]) continue;
            isVisited[curVertex] = true;

            for (Node temp = adjList[curVertex]; temp != null; temp = temp.next) {
                int nextVertex = temp.value;
                if (isVisited[nextVertex]) continue;
                if (dist[nextVertex] > dist[curVertex] + temp.weight) {
                    dist[nextVertex] = dist[curVertex] + temp.weight;
                    pq.offer(new Node(nextVertex, dist[nextVertex]));
                }
            }
        }
    }

    private static class Node {
        int value;
        int weight;
        Node next;
        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public Node(int value, int weight, Node next) {
            this.value = value;
            this.weight = weight;
            this.next = next;
        }
    }
}
