import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1094_막대기 {

    static int X;
    static int[] pow;
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        pow = new int[7];
        makePow(); // 2의 제곱수를 pow에 미리 저장하기 위한 함수

        X = Integer.parseInt(br.readLine());
        int cnt = 0;

        for(int i = 6; i>=0; i--) { // 가장 큰 수인 64부터 작아지는 순으로 2의 제곱수를 탐색
            if (X >= pow[i]) { // 현재 숫자가 제곱수보다 크거나 같으면
                cnt++; // 갯수 증가
                X -= pow[i]; // 현재 숫자에서 제곱수를 뺌
                if (X <= 0) break; // 0이 되는 경우 멈춤
            }
        }

        System.out.println(cnt);
    }

    private static void makePow() {

        pow[0] = 1;
        pow[1] = 2;
        for (int i = 2; i <= 6; i++) {
            pow[i] = pow[i-1] * 2;
        }
    }

}