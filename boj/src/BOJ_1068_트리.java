import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1068_트리 {

    static int N, S, X, answer;
    static boolean[] isVisited;
    static Node[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        adjList = new Node[N + 1];

        for (int to = 1; to <= N; to++) {
            int from = Integer.parseInt(st.nextToken()) + 1;
            adjList[from] = new Node(to, adjList[from]);
            if (from == 0) {
                S = to;
            }
        }
        X = Integer.parseInt(br.readLine()) + 1;

        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(S);
        isVisited = new boolean[N + 1];
        isVisited[S] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int curCnt = 0;

            if (cur == X) {
                continue;
            }

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (temp.vertex == X) {
                    continue;
                }

                if (isVisited[temp.vertex]) {
                    continue;
                }

                q.offer(temp.vertex);
                curCnt++;
            }

            if (curCnt == 0) {
                answer++;
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