import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거_다이어트 {
    // 조합

    static int N, limit, answer;
    static Element[] elements;
    static Element[] selectedElements;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer in;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            in = new StringTokenizer(br.readLine());
            N = Integer.parseInt(in.nextToken());
            limit = Integer.parseInt(in.nextToken());
            elements = new Element[N];
            answer = 0;

            for (int i = 0; i < N; i++) {
                in = new StringTokenizer(br.readLine());
                elements[i] = new Element(
                        Integer.parseInt(in.nextToken()),
                        Integer.parseInt(in.nextToken())
                );
            }

            Arrays.sort(elements, Comparator.comparingInt(o -> o.cal));

            for (int i = 1; i <= N; i++) {
                selectedElements = new Element[i];
                comb(0, 0, i);
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void comb(int cnt, int start, int k) {
        if (cnt == k) {
            int sum = Arrays.stream(selectedElements).mapToInt(o -> o.cal).sum();
            if (sum <= limit) {
                answer = Math.max(answer,
                        Arrays.stream(selectedElements).mapToInt(o -> o.score).sum());
            }
            return;
        }

        for (int i = start; i < N; i++) {
            selectedElements[cnt] = elements[i];
            comb(cnt + 1, i + 1, k);
        }
    }

    private static class Element {

        int score;
        int cal;

        public Element(int score, int cal) {
            this.score = score;
            this.cal = cal;
        }
    }
}
