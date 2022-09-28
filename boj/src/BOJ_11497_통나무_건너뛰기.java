import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11497_통나무_건너뛰기 {

    static int T, N;
    static int[] numbers, greedyNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            numbers = new int[N];
            greedyNumbers = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(numbers);

            int idx = 0;
            for (int i = 0; i < N; i++) {
                if (2 * i + 1 < N) {
                    greedyNumbers[i] = numbers[2 * i + 1];
                    idx = i;
                } else {
                    break;
                }
            }

            for (int i = N - 1; i > idx; i--) {
                greedyNumbers[i] = numbers[2 * (N - i - 1)];
            }

            int maxDiff = Math.abs(greedyNumbers[0] - greedyNumbers[N - 1]);
            for (int i = 0; i < N - 1; i++) {
                maxDiff = Math.max(maxDiff, Math.abs(greedyNumbers[i] - greedyNumbers[i + 1]));
            }

            sb.append(maxDiff).append("\n");
        }
        System.out.print(sb);
    }

}
