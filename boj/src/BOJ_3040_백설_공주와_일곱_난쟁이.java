import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3040_백설_공주와_일곱_난쟁이 {
    static int LIMIT = 9;
    static int TARGET = 2;
    static boolean[] isSelected = new boolean[LIMIT];
    static int[] numbers = new int[LIMIT];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < LIMIT; i++) {
            int N = Integer.parseInt(br.readLine());
            numbers[i] = N;
            isSelected[i] = true;
        }

        find(0, 0);

    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LIMIT; i++) {
            if (!isSelected[i]) {
                continue;
            }
            sb.append(numbers[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static void find(int cnt, int start) {
        if (cnt == TARGET) {
            int sum = 0;
            for (int i = 0; i < LIMIT; i++) {
                if (!isSelected[i]) {
                    continue;
                }
                sum += numbers[i];
            }
            if (sum == 100) {
                print();
            }
            return;
        }

        for (int i = start; i < LIMIT; i++) {
            isSelected[i] = false;
            find(cnt + 1, i + 1);
            isSelected[i] = true;
        }
    }
}
