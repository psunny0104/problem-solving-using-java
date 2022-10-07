import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472_다리_만들기_2 {

    static int R, C, totalLandCnt, totalBridgeCnt, bridgeLimit = 11;
    static int[][] map, bridgeDistPerLands;
    static int[][] mapToIdx;
    static boolean[][] isVisited;
    static Point[][] lands;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    static int p[];
    static Edge[] edges;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setLands();
        makeBridge();

        System.out.println(mst());

    }

    private static int mst() {

        for (int i = 1; i <= totalLandCnt; i++) {
            for (int j = 1; j <= totalLandCnt; j++) {
                if (bridgeDistPerLands[i][j] != 11) {
                    totalBridgeCnt++;
                }
            }
        }

        edges = new Edge[totalBridgeCnt];
        int idx = 0;
        for (int r = 1; r <= totalLandCnt ; r++) {
            for (int c = 1; c <= totalLandCnt; c++) {
                if (bridgeDistPerLands[r][c] != 11) {
                    edges[idx++] = new Edge(r, c, bridgeDistPerLands[r][c]);
                }
            }
        }

        makeSet();
        Arrays.sort(edges);

        int distSum = 0;
        int cnt = 0;

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                distSum += edge.dist;
                if (++cnt == totalLandCnt - 1) {
                    break;
                }
            }
        }

        if (!isAllLandsConnected()) {
            return -1;
        }

        return distSum;
    }

    private static boolean isAllLandsConnected() {
        int pivot = find(1);
        for (int i = 2; i <= totalLandCnt; i++) {
            if (pivot != find(i)) {
                return false;
            }
        }

        return true;
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }

        p[bRoot] = aRoot;
        return true;

    }

    private static int find(int a) {
        if (a == p[a]) {
            return a;
        }

        return p[a] = find(p[a]);
    }

    private static void makeSet() {
        p = new int[totalLandCnt + 1];
        for (int i = 0; i <= totalLandCnt; i++) {
            p[i] = i;
        }
    }

    private static void makeBridge() {
        bridgeDistPerLands = new int[totalLandCnt+1][totalLandCnt+1];
        for (int i = 1; i <= totalLandCnt; i++) {
            Arrays.fill(bridgeDistPerLands[i], bridgeLimit);
        }

        for (int idx = 1; idx <= totalLandCnt; idx++) {
            Point[] curLands = lands[idx];

            for (int landIdx = 0; landIdx < 100; landIdx++) {
                if (curLands[landIdx] == null) {
                    break;
                }
                Point cur = curLands[landIdx];
                for (int d = 0; d < 4; d++) {
                    int dist = 1;
                    int nr = cur.r;
                    int nc = cur.c;
                    while (true) {
                        nr += dr[d];
                        nc += dc[d];

                        if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                            break;
                        }

                        if (map[nr][nc] == 1) {
                            int pivotIdx = mapToIdx[cur.r][cur.c];
                            int targetIdx = mapToIdx[nr][nc];
                            if (pivotIdx == targetIdx) {
                                break;
                            }
                            if (dist-1 == 1) {
                                break;
                            }
                            bridgeDistPerLands[pivotIdx][targetIdx] = Math.min(
                                    bridgeDistPerLands[pivotIdx][targetIdx], dist-1);
                            bridgeDistPerLands[targetIdx][pivotIdx] = Math.min(
                                    bridgeDistPerLands[targetIdx][pivotIdx], dist-1);
                            break;
                        }

                        dist++;
                    }

                }
            }
        }
    }

    private static void setLands() {
        isVisited = new boolean[R][C];
        lands = new Point[7][100];
        mapToIdx = new int[R][C];
        totalLandCnt = 1;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (isVisited[r][c] || map[r][c] == 0) {
                    continue;
                }
                bfs(r, c, totalLandCnt++);
            }
        }
        totalLandCnt--;
    }

    private static void bfs(int r, int c, int landIdx) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        isVisited[r][c] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.r;
            int cc = cur.c;

            mapToIdx[cr][cc] = landIdx;
            lands[landIdx][cnt++] = new Point(cr, cc);

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }

                if (isVisited[nr][nc]) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    q.offer(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                }

            }
        }
    }

    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    private static class Edge implements Comparable<Edge> {

        int from, to, dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
