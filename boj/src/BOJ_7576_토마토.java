import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {

    static int y, x;
    static int[][] map;
    static int[][] dist;
    static Queue<Pair> q = new LinkedList<>();
    ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        int answer = -1;
        x = Integer.parseInt(in.nextToken());
        y = Integer.parseInt(in.nextToken());

        map = new int[y][x];
        dist = new int[y][x];

        for (int i = 0; i < y; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                map[i][j] = Integer.parseInt(in.nextToken());
                if (map[i][j] == 1) {
                    q.add(new Pair(i, j));
                    dist[i][j] = 1;
                } else if (map[i][j] == -1) {
                    dist[i][j] = -1;
                }
            }
        }

        bfs();
        answer = findAnswer();
        System.out.println(answer);
    }

    private static int findAnswer() {
        int max = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (dist[i][j] == 0) {
                    return -1;
                }
                max = Math.max(dist[i][j] - 1, max);
            }
        }
        return max;
    }

    private static void bfs() {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        while (!q.isEmpty()) {
            Pair curPair = q.poll();
            int cy = curPair.y;
            int cx = curPair.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= y || nx >= x) {
                    continue;
                }

                if (dist[ny][nx] != 0) {
                    continue;
                }

                if (map[ny][nx] != 0) {
                    continue;
                }

                dist[ny][nx] = dist[cy][cx] + 1;
                q.add(new Pair(ny, nx));
            }

        }
    }

    private static class Pair {

        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
