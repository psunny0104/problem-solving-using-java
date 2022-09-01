import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1238_Contact_ver2 {

    static int adjListCnt, startNode, MAX = 101, answer;
    static boolean[] isVisited;
    static HashMap<Integer, ArrayList<Pair>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        for (int tc = 1; tc <= 10; tc++) {
            in = new StringTokenizer(br.readLine());

            adjListCnt = Integer.parseInt(in.nextToken());
            startNode = Integer.parseInt(in.nextToken());
            isVisited = new boolean[MAX];
            adjList = new HashMap<>();

            in = new StringTokenizer(br.readLine());
            int from, to;
            for (int i = 0; i < adjListCnt / 2; i++) {
                from = Integer.parseInt(in.nextToken());
                to = Integer.parseInt(in.nextToken());
                adjList.computeIfAbsent(from, k -> new ArrayList<>());
                adjList.get(from).add(new Pair(to, 0));
            }

            bfs(startNode);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int startNode) {
        isVisited[startNode] = true;
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.level == o2.level ?
                o1.value - o2.value : o1.level - o2.level);
        pq.offer(new Pair(startNode, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            answer = cur.value;

            if (adjList.get(cur.value) == null) {
                continue;
            }

            for (Pair pair : adjList.get(cur.value)) {
                if (isVisited[pair.value]) {
                    continue;
                }

                isVisited[pair.value] = true;
                pair.level = cur.level + 1;
                pq.offer(pair);
            }
        }
    }

    private static class Pair {

        int value;
        int level;

        public Pair(int value, int level) {
            this.value = value;
            this.level = level;
        }
    }
}
