import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230_수_고르기 {

    static int N, M;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numbers);

        int start = 0;
        int end = 0;
        int minDiff = Integer.MAX_VALUE;
        while (start != N && end != N) {
            int diff = numbers[end] - numbers[start];

            if (diff >= M) {
                minDiff = Math.min(minDiff, diff);
                start++;
            } else {
                end++;
            }
        }

        System.out.println(minDiff);
    }

}
