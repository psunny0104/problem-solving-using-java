import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7795_먹을_것인가_먹힐_것인가 {

    static int T, A, B;
    static int[] arrA, arrB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            arrA = new int[A];
            for (int i = 0; i < A; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            arrB = new int[B];
            for (int i = 0; i < B; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrA);
            Arrays.sort(arrB);

            int sum = 0;

            for (int a = 0; a < A; a++) {
                int start = 0;
                int end = B - 1;

                while (start < end) {
                    int mid = (start + end) / 2;

                    if (arrB[mid] < arrA[a]) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }
                if (arrA[a] > arrB[end]) {
                    sum += end + 1;
                } else {
                    if (end > 0 && arrA[a] > arrB[end - 1]) {
                        sum += end;
                    }
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.print(sb);

    }
}