import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660_구간_합_구하기_5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int size = Integer.parseInt(in.nextToken());
        int tc = Integer.parseInt(in.nextToken());
        int[][] numbers = new int[size + 1][size + 1];
        int[][] sumMatrix = new int[size + 1][size + 1];

        for (int i = 1; i <= size; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 1; j <= size; j++) {
                numbers[i][j] = Integer.parseInt(in.nextToken());
            }
        }

        makeSumMatrix(size, numbers, sumMatrix);

        for (int t = 1; t <= tc; t++) {
            in = new StringTokenizer(br.readLine());
            int stY = Integer.parseInt(in.nextToken());
            int stX = Integer.parseInt(in.nextToken());
            int edY = Integer.parseInt(in.nextToken());
            int edX = Integer.parseInt(in.nextToken());

            int answer = getSum(stX, stY, edX, edY, sumMatrix);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);

    }

    private static void makeSumMatrix(int size, int[][] numbers, int[][] sumMatrix) {
        for (int i = 0; i <= size; i++) {
            sumMatrix[0][i] = 0;
            sumMatrix[i][0] = 0;
        }

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                sumMatrix[i][j] =
                        sumMatrix[i - 1][j] + sumMatrix[i][j - 1] + numbers[i][j] - sumMatrix[i
                                - 1][j - 1];
            }
        }
    }

    private static int getSum(int stX, int stY, int edX, int edY, int[][] sumMatrix) {
        int diff =
                sumMatrix[edY][edX] - sumMatrix[stY - 1][edX] - sumMatrix[edY][stX - 1] + sumMatrix[
                        stY - 1][stX - 1];
        return diff;
    }
}