import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2920_음계 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split(" ");
        for (String s : input) {
            sb.append(s);
        }
        switch (sb.toString()) {
            case "12345678":
                System.out.println("ascending");
                break;
            case "87654321":
                System.out.println("descending");
                break;
            default:
                System.out.println("mixed");
                break;
        }
    }
}
