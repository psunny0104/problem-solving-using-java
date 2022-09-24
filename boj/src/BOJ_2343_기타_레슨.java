import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2343_기타_레슨 {

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

        int start = Arrays.stream(numbers).max().getAsInt();
        int end = Arrays.stream(numbers).sum();

        while (start <= end) {
            int mid = (start + end) / 2;

            int curCnt = 1;
            int curSum = 0;
            for (int i = 0; i < N; i++) {
                if (curSum + numbers[i] <= mid) {
                    curSum += numbers[i];
                } else {
                    curSum = numbers[i];
                    curCnt++;
                }
            }

            if (curCnt > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }

}
