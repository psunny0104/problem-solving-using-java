import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_9935_문자열_폭발 {

    static int S, B;
    static char[] str, bomb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        str = br.readLine().toCharArray();
        bomb = br.readLine().toCharArray();

        S = str.length;
        B = bomb.length;

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < S; i++) {
            list.add(str[i]);

            int curLen = list.size();
            if (list.get(curLen - 1) == bomb[B - 1]) {
                boolean isBomb = true;
                if (curLen >= B) {
                    for (int j = 0; j < B; j++) {
                        if (bomb[j] != list.get(curLen - B + j)) {
                            isBomb = false;
                            break;
                        }
                    }
                    if (isBomb) {
                        for (int j = 0; j < B; j++) {
                            list.remove(list.size() - 1);
                        }
                    }

                }

            }
        }

        if (list.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for (Character c : list) {
                sb.append(c);
            }
            System.out.println(sb);
        }
    }

}