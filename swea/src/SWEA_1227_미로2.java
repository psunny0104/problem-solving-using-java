import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1227_미로2 {

    static int[][] map;
    static boolean[][] isVisited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int LIMIT = 100;
    static Pair START;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());

            map = new int[LIMIT][LIMIT];
            for (int i = 0; i < LIMIT; i++) {
                String in = br.readLine();
                for (int j = 0; j < LIMIT; j++) {
                    map[i][j] = in.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        START = new Pair(i, j);
                    }
                }
            }

            int answer = bfs(START.y, START.x);
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs(int y, int x) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(y, x));
        isVisited = new boolean[LIMIT][LIMIT];
        isVisited[y][x] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cy = cur.y;
            int cx = cur.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= LIMIT || nx >= LIMIT) {
                    continue;
                }

                if (isVisited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] == 1) {
                    continue;
                }

                q.offer(new Pair(ny, nx));
                isVisited[ny][nx] = true;
                if (map[ny][nx] == 3) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static class Pair {

        private int y;
        private int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
