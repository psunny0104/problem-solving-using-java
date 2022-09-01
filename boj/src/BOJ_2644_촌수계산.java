import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산 {

    static int N, adjListCnt, st, dst, answer = -1;
    static List<ArrayList<Integer>> adjList = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;
        N = Integer.parseInt(br.readLine());
        dist = new int[N + 1];

        in = new StringTokenizer(br.readLine());
        st = Integer.parseInt(in.nextToken());
        dst = Integer.parseInt(in.nextToken());

        adjListCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < adjListCnt; i++) {
            in = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(in.nextToken());
            int dst = Integer.parseInt(in.nextToken());
            adjList.get(st).add(dst);
            adjList.get(dst).add(st);
        }

        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        dist[st] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(st);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int nc : adjList.get(cur)) {
                if (dist[nc] > 0) {
                    continue;
                }

                q.offer(nc);
                dist[nc] = dist[cur] + 1;
                if (nc == dst) {
                    answer = dist[nc] - 1;
                }
            }
        }
    }
}
