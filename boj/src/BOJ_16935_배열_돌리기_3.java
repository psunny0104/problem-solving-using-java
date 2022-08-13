import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935_배열_돌리기_3 {

    static int R, C, operationCnt;
    static int[][] ogMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        R = Integer.parseInt(in.nextToken());
        C = Integer.parseInt(in.nextToken());
        operationCnt = Integer.parseInt(in.nextToken());
        ogMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            in = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                ogMap[i][j] = Integer.parseInt(in.nextToken());
            }
        }

        in = new StringTokenizer(br.readLine());
        for (int i = 0; i < operationCnt; i++) {
            int operation = Integer.parseInt(in.nextToken());
            C = ogMap[0].length;
            R = ogMap.length;
            switch (operation) {
                case 1:
                    ogMap = one();
                    break;
                case 2:
                    ogMap = two();
                    break;
                case 3:
                    ogMap = three();
                    break;
                case 4:
                    ogMap = four();
                    break;
                case 5:
                    ogMap = five();
                    break;
                case 6:
                    ogMap = six();
                    break;
            }
        }
        print();

    }

    private static void print() {
        for (int i = 0; i < ogMap.length; i++) {
            for (int j = 0; j < ogMap[i].length; j++) {
                System.out.print(ogMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] one() {
        int[][] newMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            newMap[i] = ogMap[R - i - 1];
        }
        return newMap;
    }

    private static int[][] two() {
        int[][] newMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newMap[i][j] = ogMap[i][C - j - 1];
            }
        }
        return newMap;
    }

    private static int[][] three() {
        int[][] newMap = new int[C][R];
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                newMap[i][j] = ogMap[R - j - 1][i];
            }
        }
        return newMap;
    }

    private static int[][] four() {
        int[][] newMap = new int[C][R];
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                newMap[i][j] = ogMap[j][C - i -1];
            }
        }
        return newMap;
    }

    private static int[][] five() {
        int[][] newMap = new int[R][C];
        int nr = R/2;
        int nc = C/2;

        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                newMap[i][j] = ogMap[nr + i][j];
            }

            for (int j = nc; j < C; j++) {
                newMap[i][j] = ogMap[i][j - nc];
            }
        }

        for (int i = nr; i < R; i++) {
            for (int j = 0; j < nc; j++) {
                newMap[i][j] = ogMap[i][nc + j];
            }

            for (int j = nc; j < C; j++) {
                newMap[i][j] = ogMap[i - nr][j];
            }
        }
        return newMap;
    }

    private static int[][] six() {
        int[][] newMap = new int[R][C];
        int nr = R/2;
        int nc = C/2;

        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                newMap[i][j] = ogMap[i][nc + j];
            }

            for (int j = nc; j < C; j++) {
                newMap[i][j] = ogMap[i + nr][j];
            }
        }

        for (int i = nr; i < R; i++) {
            for (int j = 0; j < nc; j++) {
                newMap[i][j] = ogMap[i - nr][j];
            }

            for (int j = nc; j < C; j++) {
                newMap[i][j] = ogMap[i][j - nc];
            }
        }
        return newMap;
    }
}
