import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로_탐색 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        int y = Integer.parseInt(in.nextToken());
        int x = Integer.parseInt(in.nextToken());
        int[][] map = new int[y + 1][x + 1];

        for (int i = 1; i <= y; i++) {
            String input = br.readLine();
            for (int j = 1; j <= x; j++) {
                map[i][j] = input.charAt(j - 1) - '0';
            }
        }

        int answer = bfs(map, y, x);
        System.out.println(answer);
    }

    private static int bfs(int[][] map, int destY, int destX) {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int[][] dist = new int[destY + 1][destX + 1];
        for (int i = 1; i <= destY; i++) {
            Arrays.fill(dist[i], 0);
        }
        dist[1][1] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1, 1));

        int answer = 0;
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cy = cur.getY();
            int cx = cur.getX();

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 1 || nx < 1 || ny > destY || nx > destX) {
                    continue;
                }

                if (dist[ny][nx] != 0) {
                    continue;
                }

                if (map[ny][nx] != 1) {
                    continue;
                }

                dist[ny][nx] = dist[cy][cx] + 1;
                q.add(new Pair(ny, nx));
                if (ny == destY && nx == destX) {
                    return dist[ny][nx];
                }
            }
        }
        return answer;
    }

    private static class Pair {

        private int y;
        private int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
}
