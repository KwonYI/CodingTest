package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_7662 {

	static int K, T;
	static TreeMap<Integer, Integer> dpq = new TreeMap<Integer, Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.valueOf(bf.readLine());

		for (int t = 0; t < T; t++) {
			K = Integer.valueOf(bf.readLine());

			for (int k = 0; k < K; k++) {
				String[] input = bf.readLine().split(" ");

				char order = input[0].charAt(0);
				int num = Integer.valueOf(input[1]);

				if (order == 'I') {
					if (dpq.containsKey(num))
						dpq.put(num, dpq.get(num) + 1);
					else
						dpq.put(num, 1);
				} else {
					if (dpq.isEmpty())
						continue;
					else {
						int key;
						if (num == 1)
							key = dpq.lastKey();
						else
							key = dpq.firstKey();

						if (dpq.get(key) > 1)
							dpq.put(key, dpq.get(key) - 1);
						else
							dpq.remove(key);

					}
				}
			}

			if (dpq.size() == 0)
				sb.append("EMPTY").append('\n');
			else
				sb.append(dpq.lastKey()).append(' ').append(dpq.firstKey()).append('\n');

			dpq.clear();
		}
		System.out.println(sb);
	}
}