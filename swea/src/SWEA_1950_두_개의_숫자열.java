import java.io.*;
import java.util.StringTokenizer;

public class SWEA_1950_두_개의_숫자열 {
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

            int[] a = new int[n];
            int[] b = new int[m];

            in = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(in.nextToken());
            }

            in = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(in.nextToken());
            }

            if (n > m) {
                int tmp = n;
                n = m;
                m = tmp;

                int[] c = a;
                a = b;
                b = c;
            }

            int endIdx = n - 1;
            int answer = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                int tmpAnswer = 0;
                if (endIdx + i > m - 1)
                    break;

                for (int j = i; j <= endIdx + i; j++) {
                    tmpAnswer += a[j - i] * b[j];
                }
                if (tmpAnswer > answer) {
                    answer = tmpAnswer;
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
