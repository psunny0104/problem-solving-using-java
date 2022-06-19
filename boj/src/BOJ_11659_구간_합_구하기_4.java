import java.io.*;
import java.util.StringTokenizer;

public class BOJ_11659_구간_합_구하기_4 {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        int[] numbers = new int[n + 1];
        int[] sumOfNumbers = new int[n + 1];
        input = new StringTokenizer(br.readLine());
        numbers[1] = Integer.parseInt(input.nextToken());
        sumOfNumbers[1] = numbers[1];
        numbers[0] = sumOfNumbers[0] = 0;

        for (int i = 2; i <= n; i++){
            numbers[i] = Integer.parseInt(input.nextToken());
            sumOfNumbers[i] = sumOfNumbers[i - 1] + numbers[i];
        }

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(input.nextToken());
            int ed = Integer.parseInt(input.nextToken());

            answer.append(sumOfNumbers[ed] - sumOfNumbers[st - 1]);
            answer.append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
    }
}
