import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003_수들의_합_2 {

    static int N, M;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = numbers[start];
        int cnt = 0;
        
        while (start != N) {
            if (sum >= M) {
                if (sum == M) {
                    cnt++;
                }
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

        System.out.println(cnt);
    }

}
