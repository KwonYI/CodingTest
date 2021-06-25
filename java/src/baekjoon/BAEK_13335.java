package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_13335 {

	static int n, w, L;
	static Queue<Integer> trucks = new LinkedList<>();
	static Queue<Integer> bridge = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			trucks.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < w; i++) {
			bridge.add(0);
		}

		int time = 0;
		int totalWeight = 0;

		while (!bridge.isEmpty()) {
			time++;
			totalWeight -= bridge.poll();

			if (!trucks.isEmpty()) {
				if (totalWeight + trucks.peek() <= L) {
					int cur = trucks.poll();
					bridge.add(cur);
					totalWeight += cur;
				} else {
					bridge.add(0);
				}
			}
		}

		System.out.println(time);
	}
}