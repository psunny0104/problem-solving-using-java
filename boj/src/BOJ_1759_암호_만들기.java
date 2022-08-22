import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호_만들기 {

    static int secretLength, kind;
    static char[] alphabets, selectedAlphabets;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer in = new StringTokenizer(br.readLine());

        secretLength = Integer.parseInt(in.nextToken());
        kind = Integer.parseInt(in.nextToken());
        alphabets = new char[kind];
        selectedAlphabets = new char[secretLength];

        in = new StringTokenizer(br.readLine());
        for (int i = 0; i < kind; i++) {
            alphabets[i] = in.nextToken().charAt(0);
        }

        Arrays.sort(alphabets);

        comb(0, 0);
        System.out.print(sb);
    }

    private static void comb(int cnt, int start) {
        if (cnt == secretLength) {
            // 체크
            if (!check()) {
                return;
            }
            for (char selectedAlphabet : selectedAlphabets) {
                sb.append(selectedAlphabet);
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < kind; i++) {
            selectedAlphabets[cnt] = alphabets[i];
            comb(cnt + 1, i + 1);
        }
    }

    private static boolean check() {
        int consonantCnt = 0;
        int vowelCnt = 0;
        for (char selectedAlphabet : selectedAlphabets) {
            if (selectedAlphabet == 'a' || selectedAlphabet == 'e' || selectedAlphabet == 'i' || selectedAlphabet == 'o' || selectedAlphabet == 'u') {
                consonantCnt++;
            } else {
                vowelCnt++;
            }
        }

        if (consonantCnt >= 1 && vowelCnt >= 2) {
            return true;
        }
        return false;
    }
}
