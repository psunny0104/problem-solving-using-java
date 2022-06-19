import java.io.*;
import java.util.StringTokenizer;

public class BOJ_11660_구간_합_구하기_5 {
    public static void main(String[] args) throws IOException {
        //firstSolution();
        secondSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        int size = Integer.parseInt(input.nextToken());
        int testCase = Integer.parseInt(input.nextToken());

        int[][] matrixOfSum = new int[size + 1][size + 1];
        matrixOfSum[0][0] = 0;

        for (int i = 1; i <= size; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 1; j <= size; j++) {
                matrixOfSum[i][j] = matrixOfSum[i][j - 1] + Integer.parseInt(input.nextToken());
            }
        }

        for (int t = 0; t < testCase; t++) {
            int sum = 0;
            input = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(input.nextToken());
            int y1 = Integer.parseInt(input.nextToken());
            int x2 = Integer.parseInt(input.nextToken());
            int y2 = Integer.parseInt(input.nextToken());

            for (int i = x1; i <= x2; i++) {
                sum += matrixOfSum[i][y2] - matrixOfSum[i][y1 - 1];
            }

            answer.append(sum);
            answer.append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void secondSolution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        int size = Integer.parseInt(input.nextToken());
        int testCase = Integer.parseInt(input.nextToken());

        int[][] matrixOfSum = new int[size + 1][size + 1];
        matrixOfSum[0][0] = 0;

        for (int i = 1; i <= size; i++) {
            input = new StringTokenizer(br.readLine());
            for (int j = 1; j <= size; j++) {
                matrixOfSum[i][j] = matrixOfSum[i][j - 1]
                        + matrixOfSum[i - 1][j]
                        - matrixOfSum[i - 1][j - 1]
                        + Integer.parseInt(input.nextToken());
            }
        }

        for (int t = 0; t < testCase; t++) {
            input = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(input.nextToken());
            int y1 = Integer.parseInt(input.nextToken());
            int x2 = Integer.parseInt(input.nextToken());
            int y2 = Integer.parseInt(input.nextToken());

            int sum = matrixOfSum[x2][y2]
                    - matrixOfSum[x1 - 1][y2]
                    - matrixOfSum[x2][y1 - 1]
                    + matrixOfSum[x1 - 1][y1 - 1];

            answer.append(sum);
            answer.append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
