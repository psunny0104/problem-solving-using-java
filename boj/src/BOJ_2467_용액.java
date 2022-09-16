import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2467_용액 {

    static int N;
    static int[] numbers, selectedNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int start = 0;
        int end = N - 1;
        int curSum;
        int minSum = Integer.MAX_VALUE;
        selectedNumbers = new int[2];

        while (start < end) {
            curSum = numbers[start] + numbers[end];
            if (Math.abs(curSum) < Math.abs(minSum)) {
                minSum = Math.abs(curSum);
                selectedNumbers[0] = numbers[start];
                selectedNumbers[1] = numbers[end];
            }
            if (curSum < 0) {
                start++;
            } else {
                end--;
            }
        }

        Arrays.sort(selectedNumbers);
        for (int selectedNumber : selectedNumbers) {
            System.out.print(selectedNumber + " ");
        }
    }

}
