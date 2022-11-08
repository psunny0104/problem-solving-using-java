import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14567_선수과목 {

    static int N, M;
    static Node[] adjList;
    static int[] inDegree;
    static int[] semester;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N + 1];
        inDegree = new int[N + 1];
        semester = new int[N + 1];
        Arrays.fill(semester, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
            inDegree[to] += 1;
        }

        topologySort();

        for (int i = 1; i <= N; i++) {
            sb.append(semester[i]).append(" ");
        }

        System.out.print(sb);
    }

    private static void topologySort() {

        Queue<Integer> q = new ArrayDeque<>();

        for (int v = 1; v <= N; v++) {
            if (inDegree[v] == 0) {
                q.offer(v);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                inDegree[temp.vertex] -= 1;
                if (inDegree[temp.vertex] == 0) {
                    q.offer(temp.vertex);
                    semester[temp.vertex] = semester[cur] + 1;
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
