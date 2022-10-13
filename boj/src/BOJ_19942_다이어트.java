import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_19942_다이어트 {

    static int N, minPriceSum = Integer.MAX_VALUE;
    static int[][] nutrients;
    static int[] pivot;
    static Deque<Integer> selected = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        pivot = new int[4];
        for (int i = 0; i < 4; i++) {
            pivot[i] = Integer.parseInt(st.nextToken());
        }

        nutrients = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                nutrients[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<Integer> stk = new ArrayDeque<>();
        search(0, 0, new int[]{0, 0, 0, 0}, stk);
        
        if (minPriceSum != Integer.MAX_VALUE) {
            sb.append(minPriceSum).append("\n");
            for (Integer integer : selected) {
                sb.append(integer).append(" ");
            }
        } else {
            sb.append(-1);
        }

        System.out.print(sb);
    }

    private static void search(int cnt, int priceSum, int[] nutrientSum, Deque<Integer> stk) {

        if (minPriceSum < priceSum) {
            return;
        }

        boolean isOver = true;
        for (int i = 0; i < 4; i++) {
            if (nutrientSum[i] < pivot[i]) {
                isOver = false;
                break;
            }
        }

        if (isOver) {
            if (minPriceSum > priceSum) {
                minPriceSum = priceSum;
                selected.clear();
                for (Integer integer : stk) {
                    selected.offer(integer + 1);
                }
            }
            return;
        }

        if (cnt == N) {
            return;
        }

        int[] newNutrientSum = Arrays.copyOf(nutrients[cnt], 4);
        for (int i = 0; i < 4; i++) {
            newNutrientSum[i] += nutrientSum[i];
        }

        stk.offer(cnt);
        search(cnt + 1, priceSum + nutrients[cnt][4], newNutrientSum, stk);
        stk.pollLast();
        search(cnt + 1, priceSum, nutrientSum, stk);

    }

}
