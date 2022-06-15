import java.util.Scanner;

public class BOJ_11720_숫자의_합_구하기 {
    public static void main(String[] args) {
        firstSolution();
    }

    private static void firstSolution() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String strNum = sc.next();

        char[] charNum = strNum.toCharArray();
        int sum = 0;
        for (int i = 0; i < strNum.length(); i++) {
            sum += charNum[i] - '0';
        }
        System.out.println(sum);
    }
}
