import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_특정한_최단_경로 {

    static int V, E, U1, U2;
    static Node[] adjList;
    static int[] distU1, distU2;

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

        st = new StringTokenizer(br.readLine());
        U1 = Integer.parseInt(st.nextToken());
        U2 = Integer.parseInt(st.nextToken());

        // start u1 u2 dst
        // start u2 u1 dst
        distU1 = new int[V + 1];
        distU2 = new int[V + 1];

        dijkstra(U1, distU1);
        dijkstra(U2, distU2);

        int answer;
        int answerU1 = 0, answerU2 = 0;
        if (distU1[1] == Integer.MAX_VALUE
                || distU1[U2] == Integer.MAX_VALUE
                || distU2[V] == Integer.MAX_VALUE) {
            answerU1 = -1;
        }
        if (distU2[1] == Integer.MAX_VALUE
                || distU2[U1] == Integer.MAX_VALUE
                || distU1[V] == Integer.MAX_VALUE) {
            answerU2 = -1;
        }

        if (answerU1 != -1 && answerU2 != -1) {
            answer = Math.min(distU2[1] + distU2[U1] + distU1[V],
                    distU1[1] + distU1[U2] + distU2[V]);
        } else if (answerU1 != -1) {
            answer = distU1[1] + distU1[U2] + distU2[V];
        } else if (answerU2 != -1) {
            answer = distU2[1] + distU2[U1] + distU1[V];
        } else {
            answer = -1;
        }

        System.out.println(answer);

    }

    private static void dijkstra(int start, int[] dist) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

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
