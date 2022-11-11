import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2056_작업 {

    static int N, answer;
    static Node[] adjList;
    static int[] inDegree;
    static int[] weight;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        adjList = new Node[N + 1];
        inDegree = new int[N + 1];
        weight = new int[N + 1];
        sum = new int[N + 1];

        for (int to = 1; to <= N; to++) {
            st = new StringTokenizer(br.readLine());
            weight[to] = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            inDegree[to] = edge;
            for (int j = 0; j < edge; j++) {
                int from = Integer.parseInt(st.nextToken());
                adjList[from] = new Node(to, adjList[from]);
            }
        }

        topologySort();

        System.out.println(answer);
    }

    private static void topologySort() {
        Queue<Integer> q = new ArrayDeque<>();

        for (int v = 1; v <= N; v++) {
            sum[v] = weight[v];
            if (inDegree[v] == 0) {
                q.offer(v);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            answer = Math.max(answer, sum[cur]);

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                inDegree[temp.vertex] -= 1;
                sum[temp.vertex] = Math.max(sum[temp.vertex], sum[cur] + weight[temp.vertex]);
                if (inDegree[temp.vertex] == 0) {
                    q.offer(temp.vertex);
                }
            }
        }
    }

    private static class Node {

        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
}