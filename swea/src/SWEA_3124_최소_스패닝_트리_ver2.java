import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_3124_최소_스패닝_트리_ver2 {

    static int T, V, E;
    static Node[] adjList;
    static int[] minEdge;
    static boolean isVisited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
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

            minEdge = new int[V + 1];
            isVisited = new boolean[V + 1];

            Arrays.fill(minEdge, Integer.MAX_VALUE);

            minEdge[1] = 0;
            long minWeightSum = 0;

            PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.weight));
            pq.offer(new Vertex(1, minEdge[1]));

            int cnt = 0;
            while (true) {
                Vertex minVertex = pq.poll();

                if (isVisited[minVertex.no]) {
                    continue;
                }
                isVisited[minVertex.no] = true;
                minWeightSum += minVertex.weight;
                if (++cnt == V) {
                    break;
                }

                for (Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
                    if (!isVisited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
                        minEdge[temp.vertex] = temp.weight;
                        pq.offer(new Vertex(temp.vertex, minEdge[temp.vertex]));
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(minWeightSum).append("\n");
        }
        System.out.print(sb);
    }

    private static class Node {

        int vertex;
        int weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

    }

    private static class Vertex {

        int no;
        int weight;

        public Vertex(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

    }
}
