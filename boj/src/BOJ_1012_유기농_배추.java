import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012_유기농_배추 {

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        int t = Integer.parseInt(br.readLine());
        int[][] map = new int[50][50];
        boolean[][] visited = new boolean[50][50];

        for (int tc = 1; tc <= t; tc++) {
            in = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(in.nextToken());
            int y = Integer.parseInt(in.nextToken());
            int cabbageCnt = Integer.parseInt(in.nextToken());
            int answer = 0;
            for (int i = 0; i < y; i++) {
                Arrays.fill(map[i], 0);
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < cabbageCnt; i++) {
                in = new StringTokenizer(br.readLine(), " ");
                int cx = Integer.parseInt(in.nextToken());
                int cy = Integer.parseInt(in.nextToken());
                map[cy][cx] = 1;
            }

            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    if (map[i][j] != 1) {
                        continue;
                    }
                    bfs(i, j, y, x, map, visited);
                    answer++;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int y, int x, int limitY, int limitX, int[][] map,
            boolean[][] visited) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int cy = pair.getY();
            int cx = pair.getX();

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (!isValid(ny, nx, limitY, limitX)) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] != 1) {
                    continue;
                }

                q.add(new Pair(ny, nx));
                visited[ny][nx] = true;
            }
        }
    }

    private static boolean isValid(int ny, int nx, int limitY, int limitX) {
        if (ny < 0 || nx < 0 || ny >= limitY || nx >= limitX) {
            return false;
        }

        return true;
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
