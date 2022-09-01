import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_8382_방향_전환 {

    static int T, MAX = 200;
    static Pair start, end;
    static boolean[][] isVisited;// 동, 북, 서, 남
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            start = new Pair(y1 + 100, x1 + 100);
            end = new Pair(y2 + 100, x2 + 100);

            int answer = Integer.MAX_VALUE;
            start.isHorizontal = true;
            start.cnt = 0;
            answer = Math.min(answer, bfs());
            start.isHorizontal = false;
            start.cnt = 0;
            answer = Math.min(answer, bfs());

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs() {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        isVisited = new boolean[MAX + 1][MAX + 1];
        isVisited[start.r][start.c] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            int dirSt = 2;
            int dirEd = 4;
            if (cur.isHorizontal) {
                dirSt = 0;
                dirEd = 2;
            }

            for (int dir = dirSt; dir < dirEd; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];

                if (nr < 0 || nc < 0 || nr > 200 || nc > 200) {
                    continue;
                }
                if (isVisited[nr][nc]) {
                    continue;
                }

                q.offer(new Pair(nr, nc, !cur.isHorizontal, cur.cnt + 1));
                isVisited[nr][nc] = true;
                if (nr == end.r && nc == end.c) {
                    return cur.cnt + 1;
                }
            }
        }
        return 0;
    }

    private static class Pair {

        int r, c;
        boolean isHorizontal;
        int cnt;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Pair(int r, int c, boolean isHorizontal, int cnt) {
            this.r = r;
            this.c = c;
            this.isHorizontal = isHorizontal;
            this.cnt = cnt;
        }
    }
}
