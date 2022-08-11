import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2468_안전_영역 {
    static int N, answer = 1;
    static int[][] map;
    static boolean[][] isVisited;
    static TreeSet<Integer> heights = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(in.nextToken());
                heights.add(map[i][j]);
            }
        }

        for (Integer height : heights) {
            init();
            getAreaCnt(height);
        }

        System.out.println(answer);
    }

    private static void init() {
        isVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(isVisited[i], false);
        }
    }

    private static void getAreaCnt(int height) {
        int curHeightCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisited[i][j]) {
                    continue;
                }
                if (map[i][j] <= height) {
                    continue;
                }
                curHeightCnt++;
                bfs(i, j, height);
            }
        }
        answer = Math.max(curHeightCnt, answer);
    }

    private static void bfs(int y, int x, int height) {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(y, x));
        isVisited[y][x] = true;

        while (!q.isEmpty()) {
            Pair cPair = q.poll();
            int cy = cPair.y;
            int cx = cPair.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    continue;
                }

                if (isVisited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] <= height) {
                    continue;
                }
                isVisited[ny][nx] = true;
                q.offer(new Pair(ny, nx));
            }
        }
    }

    private static class Pair{
        private int y;
        private int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
