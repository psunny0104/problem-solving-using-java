import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자_끼워넣기_ver2 {

    static int N, operatorCnt, minAnswer = Integer.MAX_VALUE, maxAnswer = Integer.MIN_VALUE;
    static int[] inputNumbers, operators; // +, - *, /

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        inputNumbers = new int[N];
        operators = new int[4];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputNumbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        operatorCnt = N - 1;

        perm(0, inputNumbers[0]);
        System.out.println(maxAnswer + "\n" + minAnswer);
    }

    private static void perm(int cnt, int result) {
        if (cnt == operatorCnt) {
            // 갱신
            minAnswer = Math.min(minAnswer, result);
            maxAnswer = Math.max(maxAnswer, result);

            return;
        }

        if (operators[0] > 0) {
            operators[0]--;
            perm(cnt + 1, result + inputNumbers[cnt + 1]);
            operators[0]++;
        }

        if (operators[1] > 0) {
            operators[1]--;
            perm(cnt + 1, result - inputNumbers[cnt + 1]);
            operators[1]++;
        }
        if (operators[2] > 0) {
            operators[2]--;
            perm(cnt + 1, result * inputNumbers[cnt + 1]);
            operators[2]++;
        }
        if (operators[3] > 0) {
            operators[3]--;
            perm(cnt + 1, result / inputNumbers[cnt + 1]);
            operators[3]++;
        }
    }
}
