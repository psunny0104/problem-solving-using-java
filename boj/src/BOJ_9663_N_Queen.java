import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9663_N_Queen {

    static int N, answer;
    static boolean[] col, dia1, dia2;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        col = new boolean[N*2+1];
        dia1 = new boolean[N*2+1];
        dia2 = new boolean[N*2+1];
        map = new int[N][N];

        set(0, 0);
        System.out.println(answer);
    }

    private static void set(int cnt, int r) {
        if (cnt == N) {
            answer++;

//            System.out.println(answer);
//            System.out.println("==================");
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("===================");

            return;
        }

        for (int i = 0; i < N; i++) {
            if (col[i] || dia1[i + cnt] || dia2[cnt - i + N - 1]) { // 열 체크
                continue;
            }

            col[i] = true;
            dia1[i + cnt] = true;
            dia2[cnt - i + N - 1] = true;
//            map[cnt][i] = 1;
            set(cnt + 1, 1);
//            map[cnt][i] = 0;
            col[i] = false;
            dia1[i + cnt] = false;
            dia2[cnt - i + N - 1] = false;
        }
    }
}
