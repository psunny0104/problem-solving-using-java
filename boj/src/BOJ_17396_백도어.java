import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17396_백도어 {

    static int V, E;
    static int[] canGo;
    static long[] dist;
    static boolean[] isVisited;
    static Node[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        canGo = new int[V + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++) {
            canGo[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new Node[V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) + 1;
            int to = Integer.parseInt(st.nextToken()) + 1;
            long weight = Long.parseLong(st.nextToken());

            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        dijkstra();
        long answer = dist[V] == Long.MAX_VALUE ? -1 : dist[V];
        System.out.println(answer);
    }

    private static void dijkstra() {
        dist = new long[V + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        isVisited = new boolean[V + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (isVisited[cur.vertex]) {
                continue;
            }

            isVisited[cur.vertex] = true;

            if (canGo[cur.vertex] == 1) {
                continue;
            }

            for (Node temp = adjList[cur.vertex]; temp != null; temp = temp.next) {
                if (isVisited[temp.vertex]) {
                    continue;
                }

                if (temp.vertex != V && canGo[temp.vertex] == 1) {
                    continue;
                }

                if (dist[temp.vertex] > dist[cur.vertex] + temp.weight) {
                    dist[temp.vertex] = dist[cur.vertex] + temp.weight;
                    pq.offer(new Node(temp.vertex, dist[temp.vertex]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {

        int vertex;
        long weight;
        Node next;

        public Node(int vertex, long weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public Node(int vertex, long weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.weight - o.weight);
        }
    }
}
