import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18353_병사_배치하기 {

    static int N;
    static long[] soldiers;
    static long[] dp; // LIS, 내림차순

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        soldiers = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            soldiers[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[N];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (soldiers[j] > soldiers[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(N - Arrays.stream(dp).max().getAsLong() - 1);

    }

}
