import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352_특정_거리의_도시_찾기 {

    static int cityCnt, adjListCnt, target, start;
    static Node[] adjList;
    static int[] dist;
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        cityCnt = Integer.parseInt(st.nextToken());
        adjListCnt = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        adjList = new Node[cityCnt + 1];
        dist = new int[cityCnt + 1];

        for (int i = 0; i < adjListCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
        }

        search(start);

        if (answer.isEmpty()) {
            sb.append(-1).append("\n");
        } else {
            Collections.sort(answer);
            for (Integer number : answer) {
                sb.append(number).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void search(int start) {
        dist[start] = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (dist[temp.vertex] != 0) {
                    continue;
                }

                dist[temp.vertex] = dist[cur] + 1;
                q.offer(temp.vertex);
                if (dist[temp.vertex] - 1 == target) {
                    answer.add(temp.vertex);
                }
            }
        }
    }

    static class Node {

        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
}
