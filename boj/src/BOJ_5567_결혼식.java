import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5567_결혼식 {

    static int N, M;
    static Node[] adjList;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[1] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (dist[cur] == 1 || dist[cur] == 2) {
                cnt++;
            }

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] != -1) {
                    continue;
                }

                dist[temp.vertex] = dist[cur] + 1;
                q.offer(temp.vertex);
            }
        }

        return cnt;
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
