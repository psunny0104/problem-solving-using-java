import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273_두_수의_합 {

    static int N, X;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        X = Integer.parseInt(br.readLine());
        Arrays.sort(numbers);

        int stIdx = 1;
        int endIdx = N;
        int cnt = 0;

        while (stIdx < endIdx) {
            int sum = numbers[stIdx] + numbers[endIdx];
            if (sum == X) {
                cnt++;
                stIdx++;
                endIdx--;
            } else if (sum < X) {
                stIdx++;
            } else {
                endIdx--;
            }
        }
        System.out.println(cnt);
    }

}
