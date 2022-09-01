import java.util.Scanner;

public class BOJ_1546_평균 {

    public static void main(String[] args) {
        firstSolution();
    }

    private static void firstSolution() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int highest = 0;
        int avg = 0;

        for (int i = 0; i < N; i++) {
            int input = sc.nextInt();
            avg += input;
            if (input > highest) {
                highest = input;
            }
        }

        System.out.println((100.0 * avg / N) / highest);
    }
}
