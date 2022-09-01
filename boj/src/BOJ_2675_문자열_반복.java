import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2675_문자열_반복 {

    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer in = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(in.nextToken());
        while (tc > 0) {
            in = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            int cnt = Integer.parseInt(in.nextToken());
            String target = in.nextToken();

            for (char alphabet : target.toCharArray()) {
                for (int i = 1; i <= cnt; i++) {
                    sb.append(alphabet);
                }
            }

            sb.append('\n');
            bw.write(sb.toString());
            tc--;
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
