import java.io.*;
import java.util.*;

public class BAEK_17143 {

	static class Shark {
		int r;
		int c;
		int speed; // 속도
		int dir; // 방향, 0, 1, 2, 3
		int size; // 크기

		public Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		public void move(int R, int C) {
			int r = this.r;
			int c = this.c;
			int d = this.dir;
			int moveCnt;
			
			if (d == 0 || d == 1) { // 움직인 후의 위치 계산
				moveCnt = speed % ((R - 1) * 2);
				while (moveCnt-- != 0) {
					if(!check(r + dirs[d], c)) {
						if(d == 0) d = 1;
						else d = 0;
					}
					r += dirs[d];
				}
			} else {
				moveCnt = speed % ((C - 1) * 2);
				while (moveCnt-- != 0) {
					if(!check(r, c + dirs[d])) {
						if(d == 2) d = 3;
						else d = 2;
					}
					c += dirs[d];
				}
			}
			
			this.r = r;
			this.c = c;
			this.dir = d;
		}
	}

	static int R, C, M;
	static int map[][];
	static int total;
	
	static HashMap<Integer, Shark> sharks = new HashMap<Integer, Shark>();
	static Queue<Integer> removeShark = new LinkedList<Integer>();
	
	static int[] dirs = { -1, 1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int size = Integer.parseInt(st.nextToken());

			sharks.put(size, new Shark(r, c, speed, dir, size)); // 사이즈 다르기떄문에 map사용
			map[r][c] = size;
		}

		for (int c = 1; c <= C; c++) {
			total += fishing(c);
			moveMap();
		}

		System.out.println(total);

	}

	private static int fishing(int c) {
		for (int r = 1; r <= R; r++) {
			if (map[r][c] != 0) {
				int size = map[r][c];
				sharks.remove(size);
				return size;
			}
		}
		return 0;
	}

	private static void moveMap() {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				map[r][c] = 0;
			}
		}

		for (int key : sharks.keySet()) {
			sharks.get(key).move(R, C);
		}

		
		for (int key : sharks.keySet()) {
			Shark cur = sharks.get(key);

			int r = cur.r;
			int c = cur.c;
			int size = cur.size;

			if (map[r][c] == 0)
				map[r][c] = size;
			else {
				if (map[r][c] > size) {
					removeShark.add(size);
				} else {
					removeShark.add(map[r][c]);
					map[r][c] = size;
				}
			}
		}
		
		while (!removeShark.isEmpty()) {
			sharks.remove(removeShark.poll());
		}
	}

	private static boolean check(int r, int c) {
		if (r < 1 || c < 1 || r > R || c > C) return false;
		else return true;
	}
}