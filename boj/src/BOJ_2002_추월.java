import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import sun.awt.image.ImageWatched.Link;

public class BOJ_2002_추월 {

    static int N;
    static Map<String, Integer> enterOrderMap = new HashMap<>();
    static Map<Integer, String> exitOrderMap = new HashMap<>();
    static boolean[] isUp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            enterOrderMap.put(br.readLine(), i);
        }

        for (int i = 1; i <= N; i++) {
            exitOrderMap.put(i, br.readLine());
        }

        int cnt = 0;
        isUp = new boolean[N + 1];
        for (int curIdx = 2; curIdx <= N; curIdx++) {
            for (int prevIdx = 1; prevIdx < curIdx; prevIdx++) {
                int pivot = enterOrderMap.get(exitOrderMap.get(curIdx));
                int target = enterOrderMap.get(exitOrderMap.get(prevIdx));

                if (pivot < target) {
                    if (!isUp[target]) {
                        isUp[target] = true;
                        cnt++;
                    }
                }

            }
        }

        System.out.println(cnt);

    }

}
