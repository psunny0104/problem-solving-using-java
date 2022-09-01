import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13023_ABCDE {

    static int N, M, answer;
    static boolean isOk;
    static Node[] adjList;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        for (int i = 0; i < N; i++) {
            isOk = false;
            isVisited = new boolean[N];
            dfs(i, 0);
            if (isOk) {
                answer = 1;
                break;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int idx, int cnt) {

        if (cnt == 4) {
            isOk = true;
            return;
        }

        isVisited[idx] = true;
        for (Node temp = adjList[idx]; temp != null; temp = temp.next) {
            if (isVisited[temp.value]) {
                continue;
            }

            isVisited[temp.value] = true;
            dfs(temp.value, cnt + 1);
        }
        isVisited[idx] = false;
    }

    private static class Node {

        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
