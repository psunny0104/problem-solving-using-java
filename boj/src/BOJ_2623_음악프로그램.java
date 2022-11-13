import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2623_음악프로그램 {

    static int N, M;
    static Node[] adjList;
    static int[] inDegree;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N + 1];
        inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int[] order = new int[cnt];
            for (int j = 0; j < cnt; j++) {
                order[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < cnt - 1; j++) {
                int from = order[j];
                int to = order[j + 1];
                adjList[from] = new Node(to, adjList[from]);
                inDegree[to] += 1;
            }
        }

        topologySort();

        if (answer.size() == N) {
            for (Integer cur : answer) {
                sb.append(cur).append("\n");
            }
        } else {
            sb.append(0).append("\n");
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
            answer.add(cur);

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                inDegree[temp.vertex] -= 1;
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
