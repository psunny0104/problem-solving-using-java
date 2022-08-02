import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1244_스위치_켜고_끄기 {

	static int[] buttons = new int[101];
	static int switchNum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer in;
		switchNum = Integer.parseInt(br.readLine());
		
		in = new StringTokenizer(br.readLine());
		for (int i = 1; i <= switchNum; i++) {
				buttons[i] = Integer.parseInt(in.nextToken());
		}
		
		int studentNum = Integer.parseInt(br.readLine());
		for(int i = 1; i <= studentNum; i++) {
			in = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(in.nextToken());
			int number = Integer.parseInt(in.nextToken());
			
			if(gender == 1)
				changeStateMale(number);
			else
				changeStateFemale(number);
		}
		
		for (int i = 1; i <= switchNum; i++) {
			sb.append(buttons[i]).append(" ");
			if (i % 20 == 0) {
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static void changeStateMale(int number) {
		for(int i = number; i <= switchNum; i += number ) {
			buttons[i] = 1 - buttons[i];
		}
	}

	private static void changeStateFemale(int number) {
		
		int pivot = number;
		int left = pivot - 1;
		int right = pivot + 1;
		
		buttons[pivot] = 1 - buttons[pivot];
		
		while(true) {
			if (left < 1 || right > switchNum)
				break;
			
			if (buttons[left] != buttons[right])
				break;
			
			buttons[left] = 1 - buttons[left];
			buttons[right] = 1 - buttons[right];
			
			left -= 1;
			right += 1;
		}
	}
}
	