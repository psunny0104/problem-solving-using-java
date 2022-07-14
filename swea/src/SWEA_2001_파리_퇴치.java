import java.io.*;
import java.util.StringTokenizer;

public class SWEA_2001_파리_퇴치 {
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
            int m = Integer.parseInt(in.nextToken());
            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(in.nextToken());
                }
            }

            int answer = 0;
            for(int i = 0; i < n; i++) {
                if (i + m > n)
                    continue;
                for (int j = 0; j < n; j++) {
                    if (j + m > n)
                        break;

                    int sum = 0;
                    for (int mi = i; mi < i + m; mi++) {
                        for (int mj = j; mj < j + m; mj++) {
                            sum += map[mi][mj];
                        }
                    }

                    if (sum > answer) {
                        answer = sum;
                    }
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
