import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_10816_숫자_카드_2 {

    static int N, M;
    static int[] inputNumbers;
    static Map<Integer, Integer> numberCnt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        inputNumbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputNumbers[i] = Integer.parseInt(st.nextToken());
            numberCnt.put(inputNumbers[i], numberCnt.getOrDefault(inputNumbers[i], 0) + 1);
        }

        Arrays.sort(inputNumbers);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());

            if (binarySearch(target)) {
                sb.append(numberCnt.get(target));
         } else {
                sb.append(0);
            }
            sb.append(" ");
        }
        System.out.print(sb);
    }

    private static boolean binarySearch(int target) {

        int st = 0;
        int ed = N-1;
        while (st <= ed) {
            int midIdx = (st+ed) / 2;
            int midValue = inputNumbers[midIdx];

            if (midValue == target) {
                return true;
            } else if (target > midValue) {
                st = midIdx + 1;
            } else {
                ed = midIdx - 1;
            }
        }
        return false;
    }
}
