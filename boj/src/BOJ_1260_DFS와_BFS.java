import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1260_DFS와_BFS {

    static int E, V, START;
    static ArrayList<Integer>[] adjList;
    static StringBuilder sb = new StringBuilder();
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        V = Integer.parseInt(in.nextToken());
        E = Integer.parseInt(in.nextToken());
        START = Integer.parseInt(in.nextToken());

        adjList = new ArrayList[V + 1];
        for (int i = 0; i < E; i++) {
            in = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(in.nextToken());
            int ed = Integer.parseInt(in.nextToken());
            if (adjList[st] == null) {
                adjList[st] = new ArrayList<>();
            }
            if (adjList[ed] == null) {
                adjList[ed] = new ArrayList<>();
            }
            adjList[st].add(ed);
            adjList[ed].add(st);
        }

        isVisited = new boolean[V + 1];
        dfs(START);

        sb.append("\n");

        Arrays.fill(isVisited, false);
        bfs();

        System.out.println(sb);
    }

    private static void dfs(int v) {
        isVisited[v] = true;
        sb.append(v).append(" ");

        ArrayList<Integer> curAdjList = adjList[v];
        if (curAdjList == null) {
            return;
        }

        Collections.sort(curAdjList);

        for (Integer number : curAdjList) {
            if (isVisited[number]) {
                continue;
            }
            dfs(number);
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(START);
        isVisited[START] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");
            ArrayList<Integer> curAdjList = adjList[cur];
            if (curAdjList == null) {
                continue;
            }

            Collections.sort(curAdjList);

            for (Integer number : curAdjList) {
                if (isVisited[number]) {
                    continue;
                }
                isVisited[number] = true;
                queue.offer(number);
            }
        }
    }
}
