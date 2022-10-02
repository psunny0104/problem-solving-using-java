import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5212_지구_온난화 {

    static int R, C;
    static char[][] map;
    static int[][] adjSeaCnt;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R + 1][C + 1];
        adjSeaCnt = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String input = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = input.charAt(j - 1);
            }
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 1 || nc < 1 || nr > R || nc > C) {
                        adjSeaCnt[r][c]++;
                        continue;
                    }

                    if (map[nr][nc] == '.') {
                        adjSeaCnt[r][c]++;
                    }
                }

            }
        }

        int minR, maxR, minC, maxC;
        minR = minC = 10;
        maxR = maxC = 1;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (adjSeaCnt[i][j] >= 3) {
                    map[i][j] = '.';
                }
                if (map[i][j] == 'X') {
                    minR = Math.min(minR, i);
                    minC = Math.min(minC, j);
                    maxR = Math.max(maxR, i);
                    maxC = Math.max(maxC, j);
                }
            }
        }

        for (int i = minR; i <= maxR; i++) {
            for (int j = minC; j <= maxC; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

}
