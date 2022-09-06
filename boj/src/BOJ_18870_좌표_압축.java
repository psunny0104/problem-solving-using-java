import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_18870_좌표_압축 {

    static int N;
    static int[] numbers, temp;
    static List<Integer> dupXNumbers = new ArrayList<>();
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        temp = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            temp[i] = numbers[i];
        }

        Arrays.sort(numbers);

        dupXNumbers.add(numbers[0]);
        for (int i = 1; i < N; i++) {
            if (numbers[i] != numbers[i - 1]) {
                dupXNumbers.add(numbers[i]);
            }
        }

        for (int i = 0; i < dupXNumbers.size(); i++) {
            map.put(dupXNumbers.get(i), i);
        }

        for (int number : temp) {
            sb.append(map.get(number)).append(" ");
        }

        System.out.println(sb);
    }

}
