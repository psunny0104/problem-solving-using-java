import java.io.*;
import java.util.StringTokenizer;

public class SWEA_1979_어디에_단어가_들어갈_수_있을까 {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            StringTokenizer in = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(in.nextToken());
            int k = Integer.parseInt(in.nextToken());
            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(in.nextToken());
                }
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                int rowCnt = 0;
                int colCnt = 0;

                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 1) {
                        rowCnt += 1;
                    } else if (map[i][j] == 0) {
                        if (rowCnt == k) {
                            answer += 1;
                        }
                        rowCnt = 0;
                    }
                    if (map[j][i] == 1) {
                        colCnt += 1;
                    } else if (map[j][i] == 0) {
                        if (colCnt == k) {
                            answer += 1;
                        }
                        colCnt = 0;
                    }
                }

                if (rowCnt == k) {
                    answer += 1;
                }
                if (colCnt == k) {
                    answer += 1;
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
