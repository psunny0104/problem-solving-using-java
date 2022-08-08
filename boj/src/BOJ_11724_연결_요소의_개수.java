import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11724_연결_요소의_개수 {
    static int V, E;
    static ArrayList[] adjList;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        V = Integer.parseInt(in.nextToken());
        E = Integer.parseInt(in.nextToken());

        visited = new boolean[V + 1];
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList();
        }
        for (int i = 0; i < E; i++) {
            in = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(in.nextToken());
            int ed = Integer.parseInt(in.nextToken());

            adjList[st].add(ed);
            adjList[ed].add(st);
        }

        for (int i = 1; i<= V; i++) {
            if (visited[i]) {
                continue;
            }
            dfs(i);
            answer++;
        }

        System.out.println(answer);
    }

    private static void dfs(int idx) {
        if(visited[idx])
            return;

        visited[idx] = true;
        List<Integer> curIdxAdjList = adjList[idx];

        for (Integer target : curIdxAdjList) {
            dfs(target);
        }
    }
}
