import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11050_이항_계수_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(in.nextToken());
        int K = Integer.parseInt(in.nextToken());

        K = Math.min(N - K, K);
        int answer = 1;

        for (int i = N; i > N - K; i--) {
            answer *= i;
        }

        while (K > 1) {
            answer /= K;
            K--;
        }

        System.out.println(answer);
    }
}
