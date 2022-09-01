import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {

    static int N, answer = Integer.MAX_VALUE;
    static int[] areaPeople;
    static boolean[] isSelected;
    static boolean[] isVisited;
    static Node[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        areaPeople = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            areaPeople[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new Node[N + 1];
        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(br.readLine());
            int adjCnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < adjCnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                adjList[from] = new Node(to, adjList[from]);
                adjList[to] = new Node(from, adjList[to]);
            }
        }

        isVisited = new boolean[N + 1];
        isSelected = new boolean[N + 1];

        // 가능한 개수만큼 뽑는 조합 만들기
        for (int redCnt = 1; redCnt < N; redCnt++) {
            Arrays.fill(isSelected, false);
            cmb(0, 1, redCnt);
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        System.out.println(answer);
    }


    private static void cmb(int cnt, int start, int redCnt) {
        if (cnt == redCnt) {
            // 뽑은 결과에 따라 구역 표시하기
            List<Integer> redVertices = new ArrayList<>();
            List<Integer> blueVertices = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (isSelected[i]) {
                    redVertices.add(i);
                } else {
                    blueVertices.add(i);
                }
            }

            // 선거구 검증하기
            int redVertexCnt = bfs(redVertices);
            int blueVertexCnt = bfs(blueVertices);
            if (redVertexCnt != redVertices.size()) {
                return;
            } else if (blueVertexCnt != blueVertices.size()) {
                return;
            }

            // 숫자 갱신
            int diff = getDiff(redVertices, blueVertices);
            answer = Math.min(answer, diff);

            return;
        }

        for (int i = start; i <= N; i++) {
            isSelected[i] = true;
            cmb(cnt + 1, i + 1, redCnt);
            isSelected[i] = false;
        }
    }

    private static int getDiff(List<Integer> redVertices, List<Integer> blueVertices) {
        int redSum = 0;
        for (Integer redVertex : redVertices) {
            redSum += areaPeople[redVertex];
        }

        int blueSum = 0;
        for (Integer blueVertex : blueVertices) {
            blueSum += areaPeople[blueVertex];
        }

        return Math.abs(redSum - blueSum);
    }

    private static int bfs(List<Integer> vertices) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            Arrays.fill(isVisited, false);
        }
        q.offer(vertices.get(0));
        isVisited[vertices.get(0)] = true;

        int cnt = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (isVisited[temp.vertex]) {
                    continue;
                }
                if (!vertices.contains(temp.vertex)) {
                    continue;
                }

                q.offer(temp.vertex);
                isVisited[temp.vertex] = true;
                cnt++;
            }
        }

        return cnt;
    }

    static private class Node {

        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

}
