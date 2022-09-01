import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1541_잃어버린_괄호 {

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] minusTokens = br.readLine().split("-");

        int[] subTokens = Arrays.stream(minusTokens[0].split("\\+")).mapToInt(Integer::parseInt)
                .toArray();
        answer += Arrays.stream(subTokens).sum();

        for (int i = 1; i < minusTokens.length; i++) {
            subTokens = Arrays.stream(minusTokens[i].split("\\+")).mapToInt(Integer::parseInt)
                    .toArray();
            answer -= Arrays.stream(subTokens).sum();
        }

        System.out.println(answer);
    }

}
