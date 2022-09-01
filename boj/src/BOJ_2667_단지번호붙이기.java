import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2667_단지번호붙이기 {

    static int size;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        size = Integer.parseInt(br.readLine());
        map = new char[size][];
        visited = new boolean[size][size];
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (map[i][j] == '0') {
                    continue;
                }
                numbers.add(bfs(i, j));
            }
        }

        Collections.sort(numbers);
        sb.append(numbers.size()).append("\n");
        numbers.forEach(v -> sb.append(v).append("\n"));
        System.out.print(sb);
    }

    private static int bfs(int y, int x) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        visited[y][x] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Pair curPair = q.poll();
            int cy = curPair.y;
            int cx = curPair.x;

            for (int dir = 0; dir < 4; dir++) {
                int ny = cy + dy[dir];
                int nx = cx + dx[dir];

                if (ny < 0 || nx < 0 || ny >= size || nx >= size) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] == '0') {
                    continue;
                }

                visited[ny][nx] = true;
                q.add(new Pair(ny, nx));
                cnt++;
            }
        }
        return cnt;
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
