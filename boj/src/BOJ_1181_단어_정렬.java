import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class BOJ_1181_단어_정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        HashSet<String> words = new HashSet<>();

        for(int i = 0; i < n; i++) {
            String input = br.readLine();
            words.add(input);
        }

        String[] wordArray = new String[words.size()];
        words.toArray(wordArray);

        Arrays.sort(wordArray, (String o1, String o2) -> {
            int diff = o1.length() - o2.length();
            if (diff == 0) {
//                for (int i = 0; i < o1.length(); i++) {
//                    diff = o1.charAt(i) - o2.charAt(i);
//                    if (diff != 0)
//                        break;
//                }
                return o1.compareTo(o2);
            }
            return diff;
        });

        for (String s : wordArray) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }
}