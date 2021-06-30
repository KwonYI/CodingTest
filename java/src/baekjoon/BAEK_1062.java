package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1062 {

	static int N, K;
	static int answer = 0;
	static boolean[] known = new boolean[26];
	static ArrayList<String> words = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K >= 5) {
			init();

			for (int i = 0; i < N; i++) {
				String word = bf.readLine();
				words.add(word.substring(4, word.length() - 4));
			}

			teach(0, 0);
		}

		System.out.println(answer);
	}

	public static void init() {
		known['a' - 'a'] = true;
		known['n' - 'a'] = true;
		known['t' - 'a'] = true;
		known['i' - 'a'] = true;
		known['c' - 'a'] = true;
	}

	public static void teach(int cnt, int cur) {
		if (cnt == K - 5) {
			answer = Math.max(answer, canSpeak());
			return;
		}
		for (int i = cur; i < 26; i++) {
			if (known[i]) continue;
			known[i] = true;
			teach(cnt + 1, i + 1);
			known[i] = false;
		}
	}

	public static int canSpeak() {
		int cnt = 0;

		for (String word : words) {
			boolean flag = true;
			for (int i = 0; i < word.length(); i++) {
				if (!known[word.charAt(i) - 'a']) {
					flag = false;
					break;
				}
			}

			if (flag) cnt++;
		}

		return cnt;
	}
}