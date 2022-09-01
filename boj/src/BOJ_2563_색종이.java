import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2563_색종이 {

    static int[][] map = new int[100 + 1][100 + 1];
    static int paperCnt;
    static int LEN = 10;
    static int MAX = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in;

        paperCnt = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < paperCnt; i++) {
            in = new StringTokenizer(br.readLine());
            int stX = Integer.parseInt(in.nextToken());
            int stY = Integer.parseInt(in.nextToken());

//            int offsetY = MAX - (stY + LEN) + 1;
//            int offsetX = stX + 1;
            for (int y = stY; y < stY + LEN; y++) {
                for (int x = stX; x < stX + LEN; x++) {
                    if (map[y][x] == 0) {
                        map[y][x] = 1;
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
