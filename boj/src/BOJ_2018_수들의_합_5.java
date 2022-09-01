import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2018_수들의_합_5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int st = 0;
        int ed = 0;
        int sum = 0;
        int answer = 0;

        while (st < N + 1) {
            if (sum < N) {
                sum += ed;
                ed++;
            } else if (sum > N) {
                sum -= st;
                st++;
            } else if (sum == N) {
                answer++;
                sum += ed++;
            }
        }

        System.out.println(answer);
    }
}
