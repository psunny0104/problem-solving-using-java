import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1226_미로1 {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int tc = Integer.parseInt(br.readLine());
            int[][] map = new int[16][16];

            for (int i = 0; i < 16; i++) {
                char[] in = br.readLine().toCharArray();
                for (int j = 0; j < 16; j++) {
                    map[i][j] = Integer.parseInt(String.valueOf(in[j]));
                }
            }
            int answer = bfs(map);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int[][] map) {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        boolean[][] chk = new boolean[16][16];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1});
        chk[1][1] = true;

        while (!q.isEmpty()) {
            int[] cd = q.poll();
            int y = cd[0];
            int x = cd[1];

            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];

                if (ny < 0 || nx < 0 || ny > 16 || nx > 16) {
                    continue;
                }

                if (chk[ny][nx] == true) {
                    continue;
                }

                if (map[ny][nx] == 1) {
                    continue;
                }

                if (map[ny][nx] == 0) {
                    chk[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                } else if (map[ny][nx] == 3) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
