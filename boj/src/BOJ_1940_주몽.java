import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1940_주몽 {

    static int N, M;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        int answer= 0;
        for (int i = 0; i < N; i++) {
            int first = numbers[i];
            for (int j = i + 1; j < N; j++) {
                int second = numbers[j];
                int sum = first + second;
                if (sum != M) {
                    continue;
                }
                answer++;
            }
        }

        System.out.println(answer);
    }
}
