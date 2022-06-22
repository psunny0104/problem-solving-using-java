import java.io.*;

public class BOJ_2562_최댓값 {
    public static void main(String[] args) throws IOException {
        fisrSolution();
    }

    private static void fisrSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int maxNumber = 0;
        int index = 0;

        for (int i = 1; i <= 9; i++) {
            int input = Integer.parseInt(br.readLine());

            if (input > maxNumber) {
                maxNumber = input;
                index = i;
            }
        }

        sb.append(maxNumber)
                .append('\n')
                .append(index)
                .append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
