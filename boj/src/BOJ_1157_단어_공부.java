import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1157_단어_공부 {

    public static void main(String[] args) throws IOException {
        //firstSolution();
        secondSolution();
    }

    private static void firstSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine().toUpperCase();
        HashMap<Character, Integer> alphabetList = new HashMap<Character, Integer>();

        for (char target : input.toCharArray()) {
            alphabetList.merge(target, 1, Integer::sum);
        }

        ArrayList<Map.Entry<Character, Integer>> alphabetCnt = new ArrayList<>(
                alphabetList.entrySet());
        alphabetCnt.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        sb.append(alphabetCnt.get(0).getKey());
        if (alphabetCnt.size() > 1) {
            if (alphabetCnt.get(0).getValue().equals(alphabetCnt.get(1).getValue())) {
                sb.deleteCharAt(0);
                sb.append('?');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void secondSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine().toUpperCase();
        int[] alphabetCnt = new int[26];

        for (char alphabet : input.toCharArray()) {
            alphabetCnt[alphabet - 'A'] += 1;
        }

        int maxCnt = 0;
        char maxCntAlphabet = '?';
        int isDouble = 0;

        for (int i = 0; i < 26; i++) {
            if (alphabetCnt[i] > maxCnt) {
                maxCntAlphabet = (char) ('A' + i);
                maxCnt = alphabetCnt[i];
                isDouble = 0;
            } else if (alphabetCnt[i] == maxCnt) {
                isDouble += 1;
            }
        }

        if (isDouble > 0) {
            maxCntAlphabet = '?';
        }

        sb.append(maxCntAlphabet);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
