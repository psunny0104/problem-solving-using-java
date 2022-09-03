import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1389_케빈_베이컨의_6단계_법칙 {

    static int N, M;
    static Node[] adjList;
    static int[] dist;

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
        int answer = 0;
        int minCnt = Integer.MAX_VALUE;
        for (int start = 1; start <= N; start++) {
            int cnt = bfs(start);
            if (cnt < minCnt) {
                answer = start;
                minCnt = cnt;
            }
        }
        
        System.out.println(answer);
    }

    private static int bfs(int start) {
        Arrays.fill(dist, -1);
        dist[start] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] != -1) {
                    continue;
                }

                dist[temp.vertex] = dist[cur] + 1;
                q.offer(temp.vertex);
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (i == start) {
                continue;
            }
            cnt += dist[i];
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
