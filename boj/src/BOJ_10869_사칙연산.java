import java.io.*;
import java.util.StringTokenizer;

public class BOJ_10869_사칙연산 {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(in.nextToken());
        int b = Integer.parseInt(in.nextToken());

        sb.append(a + b).append('\n')
                .append(a - b).append('\n')
                .append(a * b).append('\n')
                .append(a / b).append('\n')
                .append(a % b).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
