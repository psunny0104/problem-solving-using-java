import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10818_최소_최대 {

    public static void main(String[] args) throws IOException {
        // firstSolution();
        secondSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer in = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(in.nextToken());
        }

        Arrays.sort(numbers);
        sb.append(numbers[0])
                .append(' ')
                .append(numbers[n - 1])
                .append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void secondSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer in = new StringTokenizer(br.readLine());
        int minNumber = Integer.MAX_VALUE;
        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int target = Integer.parseInt(in.nextToken());
            minNumber = Math.min(target, minNumber);
            maxNumber = Math.max(target, maxNumber);
        }

        sb.append(minNumber)
                .append(' ')
                .append(maxNumber)
                .append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
