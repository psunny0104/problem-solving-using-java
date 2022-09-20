import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_이분_그래프 {

    static int T, V, E;
    static Node[] adjList;
    static boolean[] isVisited;
    static int[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            adjList = new Node[V + 1];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList[from] = new Node(to, adjList[from]);
                adjList[to] = new Node(from, adjList[to]);
            }

            color = new int[V + 1];
            isVisited = new boolean[V + 1];
            boolean isOk = true;
            for (int cur = 1; cur <= V; cur++) {
                if (isVisited[cur]) {
                    continue;
                }
                bfs(cur);
            }

            if (isBipartite()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean isBipartite() {
        for (int cur = 1; cur <= V; cur++) {
            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (color[temp.vertex] == color[cur]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        isVisited[start] = true;
        if (color[start] == 0) {
            color[start] = 1;
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            int curColor = color[cur];

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (isVisited[temp.vertex]) {
                    continue;
                }

                q.offer(temp.vertex);
                isVisited[temp.vertex] = true;

                int candidate = 0;
                if (color[temp.vertex] == 0) {
                    if (curColor == 1) {
                        candidate = 2;
                    } else {
                        candidate = 1;
                    }
                }

                color[temp.vertex] = candidate;
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
