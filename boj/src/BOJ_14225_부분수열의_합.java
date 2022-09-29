import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14225_부분수열의_합 {

    static int N;
    static int[] numbers, cnts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        cnts = new int[100000 * 20 + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        search(0, 0);

        for (int i = 1, len = cnts.length; i < len; i++) {
            if (cnts[i] == 0) {
                System.out.println(i);
                break;
            }
        }
    }

    private static void search(int cnt, int sum) {
        cnts[sum] += 1;

        if (cnt == N) {
            return;
        }

        search(cnt + 1, sum + numbers[cnt]);
        search(cnt + 1, sum);
    }

}
