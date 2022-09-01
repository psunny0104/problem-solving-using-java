import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서_연결하기 {

    static int T, N, coreSize, coreCntAnswer, distSumAnswer;
    static int[][] map;
    static List<Core> cores = new ArrayList<>();
    static List<Core> points = new ArrayList<>();
    static int[] dr = {0, 1, 0, -1, 0}; // 동, 남, 서, 북, 연결 안함
    static int[] dc = {1, 0, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cores.clear();

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
                            continue;
                        }
                        cores.add(new Core(i, j));
                    }
                }
            }

            coreSize = cores.size();
            coreCntAnswer = 0;
            distSumAnswer = Integer.MAX_VALUE;
            search(0, 0, 0); // 코어 인덱스, 방향, 연결된 코어 수, 길이
            sb.append("#").append(tc).append(" ").append(distSumAnswer).append("\n");
        }
        System.out.print(sb);
    }

    private static void search(int idx, int coreCnt, int distSum) {
//        System.out.println("coreCnt: "+coreCnt+" / distSum: "+distSum);
//        System.out.println("coreCntAnswer: "+coreCntAnswer+" / distSumAnswer: "+distSumAnswer);
//        print(idx);

//        if (distSum > distSumAnswer && coreCnt == coreCntAnswer) {
//            return;
//        }

        if (idx == coreSize) {
            if (coreCnt > coreCntAnswer) {
                distSumAnswer = distSum;
                coreCntAnswer = coreCnt;
            } else if (coreCnt == coreCntAnswer) {
                distSumAnswer = Math.min(distSumAnswer, distSum);
            }
            return;
        }

        for (int d = 0; d < 5; d++) {
            int connectCnt = 0;
            if (d != 4) {
                connectCnt = connect(idx, d);
                if (connectCnt == -1) {
                    continue;
                }
            }
            search(idx + 1, d == 4 ? coreCnt : coreCnt + 1, distSum + connectCnt);
            if (d != 4) {
                disconnect(idx, d);
            }
        }
    }

    private static void disconnect(int coreIdx, int dir) {
        int r = cores.get(coreIdx).r;
        int c = cores.get(coreIdx).c;

        for (int i = 0; i < N; i++) {
            r += dr[dir];
            c += dc[dir];

            if (r < 0 || c < 0 || r >= N || c >= N) {
                return;
            }

            map[r][c] = 0;
        }
    }

    private static int connect(int coreIdx, int dir) {
        int r = cores.get(coreIdx).r;
        int c = cores.get(coreIdx).c;

        int cnt = 0;
        points.clear();
        for (int i = 0; i < N; i++) {
            r += dr[dir];
            c += dc[dir];

            if (r < 0 || c < 0 || r >= N || c >= N) {
                break;
            }

            if (map[r][c] > 0) {
                return -1;
            }
//            map[r][c] = 2;
            points.add(new Core(r, c));
            cnt++;
        }
        for (Core point : points) {
            map[point.r][point.c] = 2;
        }
        return cnt;
    }

    private static void print(int idx) {
        System.out.println("선택된idx: " + idx + "====================");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=====================================");
    }

    private static class Core {

        int r, c;

        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
