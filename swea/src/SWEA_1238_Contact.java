import java.io.*;
import java.util.*;

public class SWEA_1238_Contact {
    public static void main(String[] args) throws IOException {
        firstSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;

        for (int t = 1; t <= 10; t++) {
            in = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(in.nextToken());
            int start = Integer.parseInt(in.nextToken());

            in = new StringTokenizer(br.readLine());
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            int edgeCnt = in.countTokens() / 2;

            for (int i = 0; i < edgeCnt; i++) {
                int from = Integer.parseInt(in.nextToken());
                int to = Integer.parseInt(in.nextToken());

                if (map.containsKey(from)) {
                    map.get(from).add(to);
                } else {
                    ArrayList<Integer> target = new ArrayList<>();
                    target.add(to);
                    map.put(from, target);
                }
            }

            int answer = bfs(map, start);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(HashMap<Integer, ArrayList<Integer>> map, int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] chk = new int[101];
        Arrays.fill(chk, 0);
        q.add(start);
        chk[start] = 1;

        int lastCnt = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            if (!map.containsKey(node)) {
                continue;
            }
            int size = map.get(node).size();
            for (int k = 0; k < size; k++) {
                int newNode = map.get(node).get(k);

                if (chk[newNode] != 0) {
                    continue;
                }

                chk[newNode] = chk[node] + 1;
                q.add(newNode);
                if (chk[newNode] > lastCnt) {
                    lastCnt = chk[newNode];
                }
            }
        }

        int answer = 0;
        for (int i = 100; i >= 1; i--) {
            if (chk[i] == lastCnt) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}
