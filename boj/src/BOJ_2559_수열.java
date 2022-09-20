import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559_수열 {

    static int N, K;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = start + K - 1;
        int curSum = 0;
        for (int i = start; i <= end; i++) {
            curSum += numbers[i];
        }
        int minSum = curSum;

        while (end < N - 1) {
            curSum -= numbers[start++];
            curSum += numbers[++end];
            if (minSum < curSum) {
                minSum = curSum;
            }
        }

        System.out.println(minSum);
    }

}
