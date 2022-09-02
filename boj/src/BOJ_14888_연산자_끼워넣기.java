import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_14888_연산자_끼워넣기 {

    static int N, operatorCnt, minAnswer = Integer.MAX_VALUE, maxAnswer = Integer.MIN_VALUE;
    static int[] inputNumbers, operators, selectedOperators; // +, - *, /
    static List<Integer> inputOperators = new ArrayList<>();
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        inputNumbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        operators = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < operators[i]; j++) {
                inputOperators.add(i);
            }
        }

        operatorCnt = N - 1;
        selectedOperators = new int[operatorCnt];
        isSelected = new boolean[operatorCnt];

        perm(0);
        System.out.println(maxAnswer + "\n" + minAnswer);
    }

    private static void perm(int cnt) {
        if (cnt == operatorCnt) {
            // 계산
            int result = getResult();
            // 갱신
            minAnswer = Math.min(minAnswer, result);
            maxAnswer = Math.max(maxAnswer, result);

            return;
        }

        for (int i = 0; i < operatorCnt; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            selectedOperators[cnt] = inputOperators.get(i);
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }

    private static int getResult() {
        int result = inputNumbers[0];
        for (int i = 0; i < operatorCnt; i++) {
            int operator = selectedOperators[i];
            switch (operator) {
                case 0:
                    result += inputNumbers[i + 1];
                    break;
                case 1:
                    result -= inputNumbers[i + 1];
                    break;
                case 2:
                    result *= inputNumbers[i + 1];
                    break;
                case 3:
                    result /= inputNumbers[i + 1];
                    break;
            }
        }

        return result;
    }

}
