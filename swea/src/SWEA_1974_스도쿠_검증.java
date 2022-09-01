import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1974_스도쿠_검증 {

    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            int answer = 1;
            boolean stopFlag = false;
            int[][] map = new int[10][10];

            for (int i = 1; i < 10; i++) {
                StringTokenizer in = new StringTokenizer(br.readLine());
                for (int j = 1; j < 10; j++) {
                    map[i][j] = Integer.parseInt(in.nextToken());
                }
            }
            // 가로, 세로
            int[] rowCnt = new int[10];
            int[] colCnt = new int[10];

            for (int i = 1; i < 10; i++) {
                Arrays.fill(rowCnt, 0);
                Arrays.fill(colCnt, 0);

                for (int j = 1; j < 10; j++) {
                    rowCnt[map[i][j]] = 1;
                    colCnt[map[j][i]] = 1;
                }

                for (int k = 1; k < 10; k++) {
                    if (rowCnt[k] == 0 || colCnt[k] == 0) {
                        answer = 0;
                        stopFlag = true;
                        break;
                    }
                }
                if (stopFlag) {
                    break;
                }
            }

            // 박스
            if (!stopFlag) {
                int[] boxCnt = new int[10];
                for (int i = 1; i < 10; i += 3) {
                    for (int j = 1; j < 10; j += 3) {

                        Arrays.fill(boxCnt, 0);
                        for (int box_i = i; box_i < i + 3; box_i++) {
                            for (int box_j = j; box_j < j + 3; box_j++) {
                                boxCnt[map[box_i][box_j]] += 1;
                            }
                        }

                        for (int k = 1; k < 10; k++) {
                            if (boxCnt[k] == 0) {
                                answer = 0;
                                stopFlag = true;
                                break;
                            }
                        }
                        if (stopFlag) {
                            break;
                        }
                    }
                    if (stopFlag) {
                        break;
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
