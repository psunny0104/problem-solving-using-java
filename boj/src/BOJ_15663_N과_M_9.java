import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15663_N과_M_9 {
    // 순열 -> 결과 중복 제거
    // Treeset<String> -> 그냥 붙임 -> 다시 분리할 때 구분자가 없었음
    // 구분자 추가 -> TreeSet<String> 정렬 순서가 String의 정렬으로 인해 숫자 크기 순이 아님
    // HashMap을 이용한 중복 제거, 다른 방법 찾아보기
    static int N, M;
    static boolean[] isSelected;
    static int[] numbers, inputs;
    static TreeSet<String> treeSet;
    static HashMap<String, Integer> hashMap = new LinkedHashMap<>();
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        N = Integer.parseInt(in.nextToken());
        M = Integer.parseInt(in.nextToken());

        isSelected = new boolean[N];
        numbers = new int[M];
        inputs = new int[N];
        treeSet = new TreeSet<>();

        in = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(in.nextToken());
        }

        Arrays.sort(inputs);

        perm(0);
        print();
    }

    private static void print() {
        sb = new StringBuilder();
        for (String s : hashMap.keySet()) {
            sb.append(s);
        }
        System.out.print(sb);
    }

    private static void perm(int cnt) {
        sb = new StringBuilder();
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            hashMap.putIfAbsent(sb.toString(),0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            numbers[cnt] = inputs[i];
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }
}
