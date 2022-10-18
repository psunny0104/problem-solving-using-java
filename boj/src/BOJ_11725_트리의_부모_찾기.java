import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11725_트리의_부모_찾기 {

    static int N;
    static Node[] adjList;
    static boolean[] isVisited;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        adjList = new Node[N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        isVisited[1] = true;
        q.offer(1);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (isVisited[temp.vertex]) {
                    continue;
                }

                isVisited[temp.vertex] = true;
                parents[temp.vertex] = cur;
                q.offer(temp.vertex);
            }
        }

        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }

        System.out.print(sb);
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
