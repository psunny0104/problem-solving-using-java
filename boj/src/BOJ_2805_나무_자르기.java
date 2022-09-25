import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805_나무_자르기 {

    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = Arrays.stream(trees).max().getAsInt();

        while (start < end) {
            int mid = (start + end + 1) / 2;

            long cutTreeSum = 0;
            for (int i = 0; i < N; i++) {
                if (trees[i] > mid) {
                    cutTreeSum += trees[i] - mid;
                }
            }

            if (cutTreeSum >= M) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }

}
