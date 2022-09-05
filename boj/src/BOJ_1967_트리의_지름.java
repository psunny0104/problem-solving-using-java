import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1967_트리의_지름 {

    static int V, end;
    static int[] dist;
    static Node[] adjList;
//    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        adjList = new Node[V + 1];

        for (int i = 0; i < V - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        dist = new int[V + 1];
        bfs(1);
        int answer = bfs(end);

        System.out.println(answer);
    }

    private static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        Arrays.fill(dist, -1);
        dist[start] = 0;

        int maxValue = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] != -1) {
                    continue;
                }

                dist[temp.vertex] = dist[cur] + temp.weight;
                q.offer(temp.vertex);
                if (dist[temp.vertex] > maxValue) {
                    maxValue = dist[temp.vertex];
                    end = temp.vertex;
                }
            }

        }

        return maxValue;
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
}
