import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_줄_세우기 {

    static int V, E;
    static Node[] adjList;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new Node[V + 1];
        inDegree = new int[V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
            inDegree[to] += 1;
        }

        topologySort();

        System.out.println(sb);
    }

    private static void topologySort() {
        Queue<Integer> q = new ArrayDeque<>();

        for (int v = 1; v <= V; v++) {
            if (inDegree[v] == 0) {
                q.offer(v);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur + " ");

            for (Node temp = adjList[cur]; temp != null; temp = temp.Next) {
                inDegree[temp.vertex] -= 1;
                if (inDegree[temp.vertex] == 0) {
                    q.offer(temp.vertex);
                }
            }
        }
    }

    private static class Node {

        int vertex;
        Node Next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            Next = next;
        }
    }
}
