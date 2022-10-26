import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_4386_별자리_만들기 {

    static int N;
    static Star[] stars;
    static Edge[] edgeList;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        stars = new Star[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            double c = Double.parseDouble(st.nextToken());
            double r = Double.parseDouble(st.nextToken());
            stars[i] = new Star(r, c);
        }

        int idx = 0;
        edgeList = new Edge[N * N - N];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }

                edgeList[idx++] = new Edge(i, j, Math.sqrt(
                        Math.pow(stars[i].r - stars[j].r, 2) +
                                Math.pow(stars[i].c - stars[j].c, 2)));
            }
        }

        make();
        Arrays.sort(edgeList, Comparator.comparingDouble(o -> o.weight));

        double weightSum = 0;
        int cnt = 0;

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                weightSum += edge.weight;
                cnt++;
                if (cnt == N - 1) {
                    break;
                }
            }
        }

        System.out.println(String.format("%.2f", weightSum));
    }

    private static boolean union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) {
            return false;
        }

        p[toRoot] = fromRoot;
        return true;
    }

    private static int find(int number) {
        if (number == p[number]) {
            return number;
        }

        return p[number] = find(p[number]);
    }

    private static void make() {
        p = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            p[i] = i;
        }
    }

    private static class Edge {

        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Star {

        double r, c;

        public Star(double r, double c) {
            this.r = r;
            this.c = c;
        }
    }
}
