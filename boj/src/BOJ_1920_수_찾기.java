import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_수_찾기 {

    static int numberCnt, targetCnt;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        numberCnt = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        targetCnt = Integer.parseInt(br.readLine());
        StringTokenizer in = new StringTokenizer(br.readLine());

        Arrays.sort(numbers);

        for (int i = 0; i < targetCnt; i++) {
            int target = Integer.parseInt(in.nextToken());
            if (binarySearch(target)) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static boolean binarySearch(int target) {
        int st = 0;
        int ed = numberCnt - 1;

        while (st <= ed) {
            int mid = (st + ed) / 2;
            int midValue = numbers[mid];

            if (midValue == target) {
                return true;
            } else if (midValue > target) {
                ed = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        return false;
    }
}
