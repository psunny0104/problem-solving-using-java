import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1847_스택_수열 {

    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        int idx = 1;
        for (int i = 1; i <= N; i++) {
            int curNumber = numbers[i];

            if (curNumber >= idx) {
                while (idx <= curNumber) {
                    stack.push(idx++);
                    sb.append("+").append("\n");
                }
                stack.pop();
                sb.append("-").append("\n");
            } else {
                int top = stack.pop();
                if (top > curNumber) {
                    sb = new StringBuilder();
                    sb.append("NO");
                    break;
                }
                sb.append("-").append("\n");
            }
        }

        System.out.println(sb);


    }
}
