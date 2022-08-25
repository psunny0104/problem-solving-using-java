import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4195_친구_네트워크 {

    static int T, F;
    static List<Integer> p = new ArrayList<>();
    static HashMap<String, Integer> nameIndices = new HashMap<>();
    static int hmCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            F = Integer.parseInt(br.readLine());
            p.clear();
            nameIndices.clear();
            hmCnt = 0;
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String from = st.nextToken();
                String to = st.nextToken();
                if (!nameIndices.containsKey(from)) {
                    nameIndices.put(from, hmCnt++);
                    p.add(-1);
                }
                if (!nameIndices.containsKey(to)) {
                    nameIndices.put(to, hmCnt++);
                    p.add(-1);
                }

                int fromInt = nameIndices.get(from);
                int toInt = nameIndices.get(to);
                union(fromInt, toInt);

                int target = Math.min(find(fromInt), find(toInt));
                int size = Math.abs(p.get(target));
                sb.append(size).append("\n");
            }
        }
        System.out.print(sb);

    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return;
        }

        p.set(aRoot, p.get(aRoot) + p.get(bRoot));
        p.set(bRoot, aRoot);
    }

    private static int find(int a) {
        if (p.get(a) < 0) {
            return a;
        } else {
            p.set(a, find(p.get(a)));
            return p.get(a);
        }
    }
}
