import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약 {

    static int N;
    static char[][] rgbMap;
    static char[][] normalMap;
    static boolean[][] visited;
    static int normalCnt, rgbCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        normalMap = new char[N][N];
        rgbMap = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            normalMap[i] = br.readLine().toCharArray();
            rgbMap[i] = Arrays.copyOf(normalMap[i], N);
            for (int j = 0; j < N; j++) {
                if (rgbMap[i][j] == 'G') {
                    rgbMap[i][j] = 'R';
                }
            }
        }

        normalCnt = getAreaCnt(normalMap);
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        rgbCnt = getAreaCnt(rgbMap);

        System.out.println(normalCnt + " " + rgbCnt);
    }

    private static int getAreaCnt(char[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                bfs(i, j, map);
                cnt++;
            }
        }
        return cnt;
    }

    private static void bfs(int y, int x, char[][] map) {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int cy = p.y;
            int cx = p.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] != map[y][x]) {
                    continue;
                }

                visited[ny][nx] = true;
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
