import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1873_상호의_배틀필드 {

	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			in = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(in.nextToken());
			int x = Integer.parseInt(in.nextToken());
			char[][] map = new char[y][x];
			Tank tank = null;
			
			for(int i = 0; i < y; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j < x; j++) {
					if (map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v') {
						tank = new Tank(i, j, map[i][j]);
					}
				}
			}
			
			int inputCnt = Integer.parseInt(br.readLine());
			char[] inputs = br.readLine().toCharArray();
			
			for (char input : inputs) {
				action(input, map, tank, y, x);
			}
			
			sb.append("#").append(tc).append(" ");
			print(map, y, x, sb);
		}
		System.out.println(sb);
	}

	private static void action(char input, char[][] map, Tank tank, int y, int x) {
		switch (input) {
		case 'U':
			changeDirection('^', map, tank, y, x);
			break;
		case 'D':
			changeDirection('v', map, tank, y, x);
			break;
		case 'L':
			changeDirection('<', map, tank, y, x);
			break;
		case 'R':
			changeDirection('>', map, tank, y, x);
			break;
		case 'S':
			shoot(map, tank, y, x);
			break;
		}
	}

	private static void shoot(char[][] map, Tank tank, int y, int x) {
		
		int cy = tank.getY();
		int cx = tank.getX();
		int dir = 0;
		
		switch (tank.getDir()) {
		case '^':
			dir = 0;
			break;
		case 'v':
			dir = 1;
			break;
		case '<':
			dir = 2;
			break;
		case '>':
			dir = 3;
			break;
		}
		
		while(true) {
			int ny = cy + dy[dir];
			int nx = cx + dx[dir];
			
			if(ny < 0 || nx < 0 || ny >= y || nx >= x)
				break;
			
			if(map[ny][nx] == '#') {
				break;		
			}
			
			if(map[ny][nx] == '*') {
				map[ny][nx] = '.';
				break;
			}
			
			cy = ny;
			cx = nx;
		}
	}

	private static void changeDirection(char dir, char[][] map, Tank tank, int y, int x) {
		// Up, Down, Left, Right
		int ty = tank.getY();
		int tx = tank.getX();
		tank.setDir(dir);
		map[ty][tx] = dir;
		int dirInt = 0;
		
		switch (dir) {
		case '^':
			dirInt = 0;
			break;
		case 'v':
			dirInt = 1;
			break;
		case '<':
			dirInt = 2;
			break;
		case '>':
			dirInt = 3;
			break;
		}
		
		int ny = ty + dy[dirInt];
		int nx = tx + dx[dirInt];
		
		if(ny < 0 || nx < 0 || ny >= y || nx >= x)
			return;
		
		if (map[ny][nx] == '.')
			move(dir, map, tank, ty, tx, ny, nx);
		
	}

	private static void move(char dir, char[][] map, Tank tank, int ty, int tx, int ny, int nx) {
		if(map[ny][nx] == '.') {
			map[ty][tx] = '.';
			map[ny][nx] = dir;
			tank.setY(ny);
			tank.setX(nx);
		}
	}

	private static void print(char[][] map, int y, int x, StringBuilder sb) {
		for(int i = 0; i < y; i++) {
			for (int j = 0 ; j < x; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
	}

	private static class Tank {
		private int y;
		private int x;
		private char dir;
		

		public Tank() {
			super();
		}

		public Tank(int y, int x, char dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public char getDir() {
			return dir;
		}

		public void setDir(char dir) {
			this.dir = dir;
		}
		
	}
}
