import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2839_설탕_배달 {

    static int N, cnt = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int three = 0; three <= N / 3; three++) {
            for (int five = 0; five <= N / 5 ; five++) {
                int sum = 3 * three + 5 * five;
                if (sum > N) {
                    break;
                } else if (3 * three + 5 * five == N) {
                    cnt = Math.min(cnt, three + five);
                }
            }
        }

        cnt = cnt == Integer.MAX_VALUE ? -1 : cnt;
        System.out.println(cnt);
    }
}
