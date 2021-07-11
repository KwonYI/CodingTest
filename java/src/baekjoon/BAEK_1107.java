package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1107 {

	static int target;
	static int M;
	static boolean[] broken = new boolean[10];
	static int move;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String input = bf.readLine();
		target = Integer.valueOf(input);
		move = Math.abs(target - 100);

		M = Integer.valueOf(bf.readLine());

		if (M == 0) {
			move = Math.min(move, input.length());
		} else {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}

			for (int cur = 0; cur <= 999999; cur++) {
				if (canClick(cur)) {
					int cnt = Math.abs(target - cur);
					cnt += Integer.toString(cur).length();

					move = Math.min(move, cnt);
				}
			}
		}
        
		System.out.println(move);
	}

	private static boolean canClick(int cur) {
		do {
			int m = cur % 10;
			if (broken[m])
				return false;
			cur /= 10;
		} while (cur != 0);

		return true;
	}
}