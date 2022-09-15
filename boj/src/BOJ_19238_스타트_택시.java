import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트_택시 {

    static int N, M;
    static int[][] map, dist;
    static Taxi taxi = new Taxi(0, 0);
    static List<Client> clients = new ArrayList<>();

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        taxi.fuel = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi.r = Integer.parseInt(st.nextToken());
        taxi.c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            clients.add(new Client(
                    new Point(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())),
                    new Point(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()))));
        }

        Client cur;
        int answer = 0;
        while (!clients.isEmpty()) {
            // 택시 -> 출발지 거리 갱신
            updateDistance(taxi.r, taxi.c, true);
            // 정렬
            clients.sort(Collections.reverseOrder());
            cur = clients.get(clients.size() - 1);
            // 택시 -> 출발지
            if (cur.distanceFromTaxi == -1 || taxi.fuel - cur.distanceFromTaxi < 0) {
                answer = -1;
                break;
            }
            taxi.fuel -= cur.distanceFromTaxi;
            taxi.r = cur.start.r;
            taxi.c = cur.start.c;

            // 출발지 -> 도착지 거리 갱신
            updateDistance(taxi.r, taxi.c, false);
            // 출발지 -> 도착지
            if (cur.distanceToEnd == -1 || taxi.fuel - cur.distanceToEnd < 0) {
                answer = -1;
                break;
            }
            taxi.fuel += cur.distanceToEnd;
            taxi.r = cur.end.r;
            taxi.c = cur.end.c;

            clients.remove(clients.size() - 1);
        }

        if (answer != -1) {
            answer = taxi.fuel;
        }

        System.out.println(answer);
    }

    private static void updateDistance(int startR, int startC, boolean isTaxiToStart) {
        bfs(startR, startC);

        for (Client client : clients) {
            if (isTaxiToStart) {
                client.distanceFromTaxi = getDistance(client.start);
            } else {
                client.distanceToEnd = getDistance(client.end);
            }
        }
    }

    private static void bfs(int startR, int startC) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(startR, startC));
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], -1);
        }

        dist[startR][startC] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 1 || nc < 1 || nr > N || nc > N) {
                    continue;
                }

                if (dist[nr][nc] != -1) {
                    continue;
                }

                if (map[nr][nc] == 0) {
                    q.offer(new Point(nr, nc));
                    dist[nr][nc] = dist[cr][cc] + 1;
                }
            }
        }
    }

    private static int getDistance(Point start) {
        return dist[start.r][start.c];
    }

    private static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Client implements Comparable<Client> {

        Point start;
        Point end;
        int distanceFromTaxi;
        int distanceToEnd;


        public Client(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Client o) {
            if (this.distanceFromTaxi == o.distanceFromTaxi) {
                if (this.start.r == o.start.r) {
                    return this.start.c - o.start.c;
                }
                return this.start.r - o.start.r;
            }
            return this.distanceFromTaxi - o.distanceFromTaxi;
        }
    }


    private static class Taxi extends Point {

        int fuel;

        public Taxi(int r, int c) {
            super(r, c);
        }
    }
}
