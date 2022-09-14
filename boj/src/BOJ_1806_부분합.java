import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 {

    static int N, S;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = numbers[start];
        int answer = Integer.MAX_VALUE;

        while (start != N) {
            if (sum >= S) {
                answer = Math.min(answer, end - start + 1);
                sum -= numbers[start];
                start++;
            } else {
                end++;
                if (end == N) {
                    break;
                }
                sum += numbers[end];
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }

        System.out.println(answer);
    }

}
