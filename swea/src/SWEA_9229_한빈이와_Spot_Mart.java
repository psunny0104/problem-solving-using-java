import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_9229_한빈이와_Spot_Mart {

    static int snackN, limit, answer;
    static int[] snacks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            in = new StringTokenizer(br.readLine());
            snackN = Integer.parseInt(in.nextToken());
            limit = Integer.parseInt(in.nextToken());
            snacks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            answer = -1;

            for (int i = 0; i < snackN; i++) {
                for (int j = i + 1; j <snackN; j++) {
                    int sum = snacks[i] + snacks[j];
                    if (sum > limit) {
                        continue;
                    }
                    answer = Math.max(answer, sum);
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
