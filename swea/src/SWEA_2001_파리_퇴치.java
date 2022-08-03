import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001_파리_퇴치 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        int t = Integer.parseInt(br.readLine());
        int[][] sumMatrix;

        for (int tc = 1; tc <= t; tc++) {
            in = new StringTokenizer(br.readLine(), " ");
            int matrixSize = Integer.parseInt(in.nextToken());
            int swatterSize = Integer.parseInt(in.nextToken());

            sumMatrix = new int[matrixSize + 1][matrixSize + 1];

            for (int i = 0; i <= matrixSize; i++) {
                sumMatrix[i][0] = sumMatrix[0][i] = 0;
            }

            for (int i = 1; i <= matrixSize; i++) {
                in = new StringTokenizer(br.readLine());
                for (int j = 1; j <= matrixSize; j++) {
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1];
                    sumMatrix[i][j] += Integer.parseInt(in.nextToken());
                }
            }

            int answer = 0;
            answer = searchMaxCnt(sumMatrix, matrixSize, swatterSize, answer);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");

        }
        System.out.print(sb);
    }

    private static int searchMaxCnt(int[][] sumMatrix, int matrixSize, int swatterSize, int answer) {
        int limit = matrixSize - swatterSize + 1;

        for(int i = 1; i <= limit; i++) {
            for(int j = 1; j <= limit; j++) {
                int stY = i;
                int stX = j;
                int edY = i + swatterSize - 1;
                int edX = j + swatterSize - 1;

                answer = Math.max(getSum(stX, stY, edX, edY, sumMatrix), answer);
            }
        }
        return answer;
    }

    private static int getSum(int stX, int stY, int edX, int edY, int[][] sumMatrix) {
        int diff = sumMatrix[edY][edX] - sumMatrix[stY - 1][edX] - sumMatrix[edY][stX - 1] + sumMatrix[stY - 1][stX - 1];
        return diff;
    }

}
