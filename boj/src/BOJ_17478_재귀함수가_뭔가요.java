import java.util.Scanner;
import java.io.IOException;

public class BOJ_17478_재귀함수가_뭔가요  {

    static StringBuilder sb = new StringBuilder();
    static String UNDERBAR = "____";

    static String first = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";;
    static String[] stmts = {
            "\"재귀함수가 뭔가요?\"\n",
            "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
            "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n",
    };
    static String center = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    static String last = "라고 답변하였지.\n";

    static int N;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        sb.append(first);
        recursivePrint(N);
        System.out.println(sb);
    }

    private static void recursivePrint(int n) throws IOException {

        appendUnderbar(n);
        sb.append(stmts[0]);

        if (n == 0) {
            appendUnderbar(n);
            sb.append(center);
            appendUnderbar(n);
            sb.append(last);
            return;
        }
        // ELSE
        for(int i = 1; i <= 3; i++) {
            appendUnderbar(n);
            sb.append(stmts[i]);
        }

        recursivePrint(n - 1);

        appendUnderbar(n);
        sb.append(last);

        return;
    }


    private static void appendUnderbar(int cnt) {
        for(int i = N; i > cnt; i--) {
            sb.append(UNDERBAR);
        }
    }
}
