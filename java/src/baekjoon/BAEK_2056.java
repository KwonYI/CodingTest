package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_2056 {

	static int N;
	static int[] times, degrees, minTimes;
	static ArrayList<Integer>[] preWork;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		times = new int[N + 1];
		degrees = new int[N + 1];
		minTimes = new int[N + 1];
		preWork = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			preWork[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int time = Integer.parseInt(st.nextToken());
			minTimes[i] = times[i] = time;

			int cnt = Integer.parseInt(st.nextToken());
			degrees[i] += cnt;

			for (int j = 0; j < cnt; j++) {
				int index = Integer.parseInt(st.nextToken());
				preWork[index].add(i);
			}
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (degrees[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int next : preWork[cur]) {
				if (minTimes[next] < minTimes[cur] + times[next]) {
					minTimes[next] = minTimes[cur] + times[next];
				}

				if (--degrees[next] == 0) {
					q.add(next);
				}
			}
		}
		
		Arrays.sort(minTimes);
		System.out.println(minTimes[N]);

	}
}