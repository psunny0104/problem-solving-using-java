import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4963_섬의_개수 {

    static int R, C;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dr = {0, 0, 1, -1, 1, -1, 1, -1};
    static int[] dc = {1, -1, 0, 0, 1, -1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        while (true) {
            in = new StringTokenizer(br.readLine());
            C = Integer.parseInt(in.nextToken());
            R = Integer.parseInt(in.nextToken());

            if (C == 0 && R == 0) {
                break;
            }

            int cnt = 0;
            map = new int[R][C];
            isVisited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(in.nextToken());
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (isVisited[i][j]) {
                        continue;
                    }
                    if (map[i][j] == 1) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int r, int c) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(r, c));
        isVisited[r][c] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int dir = 0; dir < 8; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];

                if (nr < 0 || nc <0 || nr >= R || nc >= C) continue;
                if (isVisited[nr][nc]) continue;
                if (map[nr][nc] == 0) continue;

                queue.offer(new Pair(nr, nc));
                isVisited[nr][nc] = true;
            }
        }
    }

    private static class Pair{
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
