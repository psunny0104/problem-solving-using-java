import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1208_Flatten {

    static int LENGTH = 100;
    static int[] heights = new int[LENGTH + 1];
    static int dumpCnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input;

        for (int tc = 1; tc <= 10; tc++) {
            dumpCnt = Integer.parseInt(br.readLine());
            input = new StringTokenizer(br.readLine());

            for (int i = 0; i < LENGTH; i++) {
                heights[i] = Integer.parseInt(input.nextToken());
            }

            int answer = 0;

            while (dumpCnt > 0) {
                dump();
                answer = chk();
                dumpCnt--;
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dump() {
        int highIdx = 0;
        int lowIdx = 0;

        for (int i = 0; i < LENGTH; i++) {
            if (heights[highIdx] <= heights[i]) {
                highIdx = i;
            }
            if (heights[lowIdx] >= heights[i]) {
                lowIdx = i;
            }
        }

        heights[highIdx] -= 1;
        heights[lowIdx] += 1;
    }

    private static int chk() {
        int highIdx = 0;
        int lowIdx = 0;

        for (int i = 0; i < LENGTH; i++) {
            if (heights[highIdx] <= heights[i]) {
                highIdx = i;
            }
            if (heights[lowIdx] >= heights[i]) {
                lowIdx = i;
            }
        }

        return heights[highIdx] - heights[lowIdx];
    }

}
