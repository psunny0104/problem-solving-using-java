import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의_합 {

    static int N, S, answer;
    static int[] numbers;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        S = Integer.parseInt(in.nextToken());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        isSelected = new boolean[N];

        Arrays.sort(numbers);
        search(0, 0);
        answer = S == 0 ? answer - 1 : answer;
        System.out.println(answer);
    }

    private static void search(int cnt, int sum) {
//        if (sum > S) return;

        if (cnt == N) {
            if (sum == S) {
                answer++;
            }
            return;
        }

        search(cnt + 1, sum + numbers[cnt]);
        search(cnt + 1, sum);

    }
}
