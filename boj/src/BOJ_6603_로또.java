import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603_로또 {

    static int K;
    static int[] numbers, selectedNumbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if (K == 0) {
                break;
            }

            numbers = new int[K];
            selectedNumbers = new int[6];

            for (int i = 0; i < K; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            makeSet();
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void makeSet() {
        cmb(0, 0);
    }

    private static void cmb(int cnt, int start) {
        if (cnt == 6) {
            for (int selectedNumber : selectedNumbers) {
                sb.append(selectedNumber).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < K; i++) {
            selectedNumbers[cnt] = numbers[i];
            cmb(cnt + 1, i + 1);
        }
    }

}
