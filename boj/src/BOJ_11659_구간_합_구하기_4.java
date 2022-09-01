import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11659_구간_합_구하기_4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int size = Integer.parseInt(in.nextToken());
        int tc = Integer.parseInt(in.nextToken());
        int[] numbers = new int[size + 1];
        int[] sumArr = new int[size + 1];

        in = new StringTokenizer(br.readLine());
        for (int i = 1; i <= size; i++) {
            numbers[i] = Integer.parseInt(in.nextToken());
        }

        makeSumArr(size, numbers, sumArr);

        for (int t = 1; t <= tc; t++) {
            in = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(in.nextToken());
            int ed = Integer.parseInt(in.nextToken());

            int answer = getSum(st, ed, sumArr);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void makeSumArr(int size, int[] numbers, int[] sumArr) {
        sumArr[0] = numbers[0] = 0;
        for (int i = 1; i <= size; i++) {
            sumArr[i] = sumArr[i - 1] + numbers[i];
        }
    }

    private static int getSum(int st, int ed, int[] sumArr) {
        int diff = sumArr[ed] - sumArr[st - 1];
        return diff;
    }

}
