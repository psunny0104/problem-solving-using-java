import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16637_괄호_추가하기 {

    static int N, maxSum = Integer.MIN_VALUE;
    static String exp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        exp = br.readLine();

        dfs(0, exp.charAt(0) - '0');
        System.out.println(maxSum);
    }

    private static void dfs(int cnt, int sum) {
        if (cnt + 2 >= N) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        // 괄호 O
        if (cnt + 4 <= N - 1) {
            int nextSum = operate(exp.charAt(cnt + 2) - '0', exp.charAt(cnt + 3),
                    exp.charAt(cnt + 4) - '0');
            dfs(cnt + 4, operate(sum, exp.charAt(cnt + 1), nextSum));
        }

        // 괄호 X
        dfs(cnt + 2, operate(sum, exp.charAt(cnt + 1), exp.charAt(cnt + 2) - '0'));
    }

    private static int operate(int a, char op, int b) {
        int ret = a;

        switch (op) {
            case '+':
                ret += b;
                break;
            case '-':
                ret -= b;
                break;
            case '*':
                ret *= b;
                break;
        }

        return ret;
    }

}
