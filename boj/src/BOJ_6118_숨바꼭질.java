import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6118_숨바꼭질 {

    static int N, M, max = Integer.MIN_VALUE;
    static Node[] adjList;
    static int[] dist; // level

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        dist = new int[N + 1];
        bfs(1);

        int answer = 0;
        int cnt = 0;

        for (int i = N; i >= 1; i--) {
            if (dist[i] != max) {
                continue;
            }
            answer = i;
            cnt++;
        }

        System.out.println(answer +" "+max+" "+cnt);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        Arrays.fill(dist, -1);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            max = Math.max(max, dist[cur]);

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] != -1) {
                    continue;
                }

                dist[temp.vertex] = dist[cur] + 1;
                q.offer(temp.vertex);
            }
        }
    }

    private static class Node {
        int vertex;
        int level;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
}
