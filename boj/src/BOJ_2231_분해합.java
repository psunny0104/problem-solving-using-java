import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2231_분해합 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int target = i;
            int sum = i;
            while (target > 0) {
                sum += target % 10;
                target /= 10;
            }
            if (sum == N) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}
