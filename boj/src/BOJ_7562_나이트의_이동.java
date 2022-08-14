import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562_나이트의_이동 {

    static int N;
    static Pair start, dest;
    static int[] dr = {-1, -2 ,-2 ,-1 ,1, 2, 2, 1};
    static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            in = new StringTokenizer(br.readLine());
            start = new Pair(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));
            in = new StringTokenizer(br.readLine());
            dest = new Pair(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));

            int answer = bfs();
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        int[][] dist = new int[N][N];
        dist[start.r][start.c] = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int dir = 0; dir < dr.length; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (dist[nr][nc] > 0) continue;

                q.offer(new Pair(nr, nc));
                dist[nr][nc] = dist[cr][cc] + 1;

                if (nr == dest.r && nc == dest.c) {
                    return dist[nr][nc] - 1;
                }
            }
        }
        return 0;
    }

    private static class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
