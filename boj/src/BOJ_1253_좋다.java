import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1253_좋다 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(numbers);

        int answer = 0;
        for (int curIdx = 0; curIdx < N; curIdx++) {
            int target = numbers[curIdx];
            int st = 0;
            int ed = N - 1;
            int sum;
            while (st < ed) {
                sum = numbers[st] + numbers[ed];
                if (sum == target) {
                    if (st == curIdx) {
                        st++;
                    } else if (ed == curIdx) {
                        ed--;
                    } else {
                        answer++;
                        break;
                    }
                } else if (sum < target) {
                    st++;
                } else {
                    ed--;
                }
            }
        }
        System.out.println(answer);
    }
}
