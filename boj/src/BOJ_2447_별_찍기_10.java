import java.util.Scanner;

public class BOJ_2447_별_찍기_10 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                go(i, j, N);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void go(int y, int x, int n) {
        if ((y / n) % 3 == 1 && (x / n) % 3 == 1) {
            sb.append(" ");
        } else {
            if (n / 3 == 0) {
                sb.append("*");
            } else {
                go(y, x, n / 3);
            }
        }
    }
}
