import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_6443_애너그램 {

    static int N, LIMIT;
    static char[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            input = br.readLine().toCharArray();
            LIMIT = input.length;

            Arrays.sort(input);

            do {
                sb.append(input).append("\n");
            } while (np(input));
        }

        System.out.print(sb);
    }

    private static boolean np(char[] input) {
        int LEN = input.length;

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
