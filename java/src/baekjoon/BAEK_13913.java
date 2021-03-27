package baekjoon;

import java.util.*;

public class BAEK_13913 {
	static int N, K;
	static int[] dir = { -1, 1, 0 };
	static int[] route = new int[100001];
	static int[] time = new int[100001];

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt();
		K = sc.nextInt();

		find();

		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		int target = K;

		while (target != N) {
			stack.push(route[target]);
			target = route[target];
		}

		sb.append(time[K] - 1).append("\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(' ');
		}

		System.out.println(sb.toString());

		sc.close();
	}

	static void find() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);
		time[N]++;

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == K)
				return;

			int next;
			for (int d : dir) {
				if (d == 0)	next = cur * 2;
				else next = cur + d;

				if (next < 0 || next > 100000) continue;

				if (time[next] == 0) {
					q.add(next);
					time[next] = time[cur] + 1;
					route[next] = cur;
				}
			}
		}
	}
}
