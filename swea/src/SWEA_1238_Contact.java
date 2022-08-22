import java.io.*;
import java.util.*;

public class SWEA_1238_Contact {

    static int adjListCnt, startNode, MAX = 100;
    static int[] dist;
    static HashMap<Integer, ArrayList<Integer>> adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        for (int tc = 1; tc <= 10; tc++) {
            in = new StringTokenizer(br.readLine());
            adjListCnt = Integer.parseInt(in.nextToken());
            startNode = Integer.parseInt(in.nextToken());
            adjList = new HashMap<>();

            in = new StringTokenizer(br.readLine());
            for (int i = 0; i < adjListCnt/2; i++) {
                int from = Integer.parseInt(in.nextToken());
                int to = Integer.parseInt(in.nextToken());

                adjList.computeIfAbsent(from, k -> new ArrayList<>());
                adjList.get(from).add(to);
            }

            int answer = bfs();
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs() {
        dist = new int[MAX + 1];
        dist[startNode] = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(startNode);
        int lastLevel = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (!adjList.containsKey(cur)) {
                continue;
            }

            List<Integer> nextNodes = adjList.get(cur);
            for (Integer next : nextNodes) {
                if (dist[next] > 0) {
                    continue;
                }

                dist[next] = dist[cur] + 1;
                q.offer(next);
                lastLevel = Math.max(lastLevel, dist[next]);
            }
        }

        return getAnswerNode(lastLevel);
    }

    private static int getAnswerNode(int lastLevel) {
        int answer = 0;

        for (int i = MAX; i >= 1; i--) {
            if (dist[i] == lastLevel) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}
