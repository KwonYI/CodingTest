package baekjoon;

import java.util.*;

public class BAEK_1699 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] p = new int[N + 1];

		for (int cur = 1; cur <= N; cur++) {
			p[cur] = cur;
			int num = (int) Math.sqrt(cur);
			while (cur >= num * num) {
				p[cur] = Math.min(p[cur], p[cur - num * num] + 1);
				num--;
			}
		}

		System.out.println(p[N]);
		sc.close();
	}
}