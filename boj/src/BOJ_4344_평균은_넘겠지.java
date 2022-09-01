import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_4344_평균은_넘겠지 {

    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while (tc > 0) {
            StringTokenizer in = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(in.nextToken());
            int[] scores = new int[size];
            int sum = 0;
            double avgScore = 0;

            for (int i = 0; i < size; i++) {
                scores[i] = Integer.parseInt(in.nextToken());
                sum += scores[i];
            }

            avgScore = sum / (double) size;
            int cnt = 0;

            for (int i = 0; i < size; i++) {
                if (scores[i] > avgScore) {
                    cnt += 1;
                }
            }

            double answer = 100.0 * cnt / size;
            sb.append(String.format("%.3f", answer)).append("%\n");
            tc--;
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
