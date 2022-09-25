import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654_랜선_자르기 {

    static int K, N;
    static int[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lines = new int[K];

        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        long start = 1;
        long end = Integer.MAX_VALUE;

        while (start <= end) {
            long mid = (start + end) / 2;

            long curSum = 0;
            for (int i = 0; i < K; i++) {
                curSum += lines[i] / mid;
            }

            if (curSum >= N) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }

}
