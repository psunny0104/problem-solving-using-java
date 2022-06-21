import java.io.*;
import java.util.StringTokenizer;

public class BOJ_8958_OX퀴즈 {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer in = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(in.nextToken());
        while (tc > 0) {
            String input = br.readLine();
            int len = input.length();
            int score = 0;
            int scoreToBeAdded = 1;

            for (int i = 0; i < len; i++) {
                if (input.charAt(i) == 'O') {
                    score += scoreToBeAdded;
                    scoreToBeAdded += 1;
                } else {
                    scoreToBeAdded = 1;
                }
            }
            sb.append(score).append('\n');
            tc--;
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
