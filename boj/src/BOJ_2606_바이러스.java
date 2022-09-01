import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2606_바이러스 {

    static int comCnt, adjCnt;
    static HashMap<Integer, List<Integer>> adjList;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;

        comCnt = Integer.parseInt(br.readLine());
        adjCnt = Integer.parseInt(br.readLine());
        adjList = new LinkedHashMap<>();
        visited = new boolean[comCnt + 1];
        for (int i = 0; i < adjCnt; i++) {
            in = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(in.nextToken());
            int ed = Integer.parseInt(in.nextToken());
            makeAdjList(st, ed);
            makeAdjList(ed, st);
        }

        visited[1] = true;
        dfs(1);
        System.out.println(answer);
    }

    private static void makeAdjList(int st, int ed) {
        if (adjList.containsKey(st)) {
            adjList.get(st).add(ed);
        } else {
            adjList.put(st, new ArrayList<>());
            adjList.get(st).add(ed);
        }
    }

    private static void dfs(int idx) {
        if (adjList.get(idx) == null) {
            return;
        }

        List<Integer> candidates = new ArrayList<>(adjList.get(idx));
        Collections.sort(candidates);
        for (Integer dst : candidates) {
            if (visited[dst]) {
                continue;
            }

            answer++;
            visited[dst] = true;
            dfs(dst);
        }
    }
}