import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9081_단어_맞추기 {

    static int T, LEN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            char[] input = br.readLine().toCharArray();
            LEN = input.length;
            np(input);
            sb.append(input).append("\n");
        }

        System.out.print(sb);
    }

    private static boolean np(char[] input) {

        int i = LEN - 1;
        while (i > 0 && input[i - 1] >= input[i]) {
            --i;
        }

        if (i == 0) {
            return false;
        }

        int j = LEN - 1;
        while (input[i - 1] >= input[j]) {
            --j;
        }

        swap(input, i - 1, j);

        int k = LEN - 1;
        while (i < k) {
            swap(input, i++, k--);
        }

        return true;
    }

    private static void swap(char[] input, int i, int j) {
        char tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }


}
