import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11779_최소비용_구하기_2 {

    static int V, E, start, end;
    static Vertex[] adjList;
    static int[] dist, prev;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        adjList = new Vertex[V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from] = new Vertex(to, weight, adjList[from]);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dist = new int[V + 1];
        prev = new int[V + 1];
        isVisited = new boolean[V + 1];

        dijkstra();
        int result = dist[end];
        System.out.println(result);
        findPath();
    }

    private static void findPath() {
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> paths = new ArrayList<>();

        paths.add(end);
        do {
            end = prev[end];
            paths.add(end);
        } while (end != start);

//        paths.sort(Comparator.naturalOrder());

        int size = paths.size();
        for (int i = size - 1; i >= 0; i--) {
            sb.append(paths.get(i)).append(" ");
        }

        System.out.println(size);
        System.out.print(sb.substring(0, sb.length() - 1));
    }

    private static void dijkstra() {
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();

            if (isVisited[cur.vertex]) {
                continue;
            }

            isVisited[cur.vertex] = true;

            for (Vertex temp = adjList[cur.vertex]; temp != null; temp = temp.next) {
                if (isVisited[temp.vertex]) {
                    continue;
                }

                if (dist[temp.vertex] > dist[cur.vertex] + temp.weight) {
                    dist[temp.vertex] = dist[cur.vertex] + temp.weight;
                    prev[temp.vertex] = cur.vertex;
                    pq.offer(new Vertex(temp.vertex, dist[temp.vertex]));
                }

            }
        }

    }


    private static class Vertex implements Comparable<Vertex> {

        int vertex;
        int weight;
        Vertex next;

        public Vertex(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public Vertex(int vertex, int weight, Vertex next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }


        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }
    }
}
