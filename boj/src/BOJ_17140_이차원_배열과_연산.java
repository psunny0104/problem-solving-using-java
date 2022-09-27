import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17140_이차원_배열과_연산 {

    static int R, C, K;
    static int rLen = 3, cLen = 3;
    static int[][] ogMap, cpMap;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ogMap = new int[300][300];
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                ogMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (ogMap[R][C] != K) {
            doOperation(rLen >= cLen ? 1 : 2);
            time++;
            if (time == 101) {
                time = -1;
                break;
            }
        }

        System.out.println(time);
    }

    private static void doOperation(int operation) {
        cpMap = new int[300][300];
        if (operation == 1) {
            int maxIdx = 0;
            for (int i = 1; i <= rLen; i++) {
                PriorityQueue<Point> pq = new PriorityQueue<>();
                int[] numberCnt = new int[101];
                int max = 0;

                for (int j = 1; j <= cLen; j++) {
                    if (ogMap[i][j] == 0) {
                        continue;
                    }
                    numberCnt[ogMap[i][j]] += 1;
                    max = Math.max(max, ogMap[i][j]);
                }

                for (int j = 1; j <= max; j++) {
                    if (numberCnt[j] == 0) {
                        continue;
                    }
                    pq.offer(new Point(j, numberCnt[j]));
                }

                int idx = 1;
                while (!pq.isEmpty()) {
                    Point cur = pq.poll();
                    cpMap[i][idx++] = cur.number;
                    cpMap[i][idx++] = cur.cnt;
                }
                maxIdx = Math.max(maxIdx, idx - 1);
            }
            cLen = Math.min(maxIdx, 100);
        } else {
            int maxIdx = 0;
            for (int i = 1; i <= cLen; i++) {
                PriorityQueue<Point> pq = new PriorityQueue<>();
                int[] numberCnt = new int[101];
                int max = 0;

                for (int j = 1; j <= rLen; j++) {
                    if (ogMap[j][i] == 0) {
                        continue;
                    }
                    numberCnt[ogMap[j][i]] += 1;
                    max = Math.max(max, ogMap[j][i]);
                }

                for (int j = 1; j <= max; j++) {
                    if (numberCnt[j] == 0) {
                        continue;
                    }
                    pq.offer(new Point(j, numberCnt[j]));
                }

                int idx = 1;
                while (!pq.isEmpty()) {
                    Point cur = pq.poll();
                    cpMap[idx++][i] = cur.number;
                    cpMap[idx++][i] = cur.cnt;
                }
                maxIdx = Math.max(maxIdx, idx - 1);
            }
            rLen = Math.min(maxIdx, 100);
        }
        ogMap = cpMap;
    }

    private static class Point implements Comparable<Point> {

        int number;
        int cnt;

        public Point(int number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            if (this.cnt == o.cnt) {
                return this.number - o.number;
            }
            return this.cnt - o.cnt;
        }
    }
}
