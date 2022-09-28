import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2660_회장뽑기 {

    static int N, minCnt = Integer.MAX_VALUE;
    static Node[] adjList;
    static int[] dist, minDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        adjList = new Node[N + 1];
        minDist = new int[N + 1];
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (from == -1 && to == -1) {
                break;
            }
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        for (int i = 1; i <= N; i++) {
            minDist[i] = bfs(i);
            minCnt = Math.min(minCnt, minDist[i]);
        }

        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (minDist[i] == minCnt) {
                candidates.add(i);
            }
        }

        sb.append(minCnt).append(" ").append(candidates.size()).append("\n");
        for (Integer candidate : candidates) {
            sb.append(candidate).append(" ");
        }
        System.out.println(sb);
    }

    private static int bfs(int c) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(c);
        dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[c] = 0;

        int curMax = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int cur = q.poll();

            curMax = Math.max(curMax, dist[cur]);

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] != -1) {
                    continue;
                }

                dist[temp.vertex] = dist[cur] + 1;
                q.offer(temp.vertex);
            }
        }

        return curMax;
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
