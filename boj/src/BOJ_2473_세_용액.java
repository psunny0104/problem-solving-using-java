import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473_세_용액 {

    static int N;
    static long[] numbers, selectedNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        selectedNumbers = new long[3];
        long minSum = Long.MAX_VALUE;
        for (int first = 0; first < N; first++) {
            int second = 0;
            int third = N - 1;
            long curSum;

            while (second < third) {
                if (first == second) {
                    second++;
                    continue;
                } else if (first == third) {
                    third--;
                    continue;
                }

                curSum = numbers[first] + numbers[second] + numbers[third];

                if (Math.abs(minSum) > Math.abs(curSum)) {
                    minSum = Math.abs(curSum);
                    selectedNumbers[0] = numbers[first];
                    selectedNumbers[1] = numbers[second];
                    selectedNumbers[2] = numbers[third];
                }
                if (curSum < 0) {
                    second++;
                } else {
                    third--;
                }
            }
        }

        Arrays.sort(selectedNumbers);
        for (long selectedNumber : selectedNumbers) {
            System.out.print(selectedNumber + " ");
        }

    }

}
