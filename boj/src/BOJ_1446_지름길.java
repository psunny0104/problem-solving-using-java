import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1446_지름길 {

    static int N, D, LIMIT = 10000;
    static int[] dp;
    static Map<Integer, List<Edge>> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (hashMap.containsKey(to)) {
                hashMap.get(to).add(new Edge(from, to, weight));
            } else {
                List<Edge> temp = new ArrayList<>();
                temp.add(new Edge(from, to, weight));
                hashMap.put(to, temp);
            }
        }

        dp = new int[D + 1];

        for (int i = 1; i <= D; i++) {
            if (hashMap.containsKey(i)) {
                int candidate = Integer.MAX_VALUE;
                for (Edge edge : hashMap.get(i)) {
                    candidate = Math.min(candidate,
                            Math.min(dp[i - 1] + 1, dp[edge.from] + edge.weight));
                }
                dp[i] = candidate;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        System.out.println(dp[D]);
    }

    private static class Edge {

        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}
