import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BOJ_20291_파일_정리 {

    static int N;
    static Map<String, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String target = br.readLine().split("\\.")[1];

            if (treeMap.containsKey(target)) {
                treeMap.put(target, treeMap.get(target) + 1);
            } else {
                treeMap.put(target, 1);
            }
        }

        for (Entry<String, Integer> entry : treeMap.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        System.out.print(sb);
    }

}
