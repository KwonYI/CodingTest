package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_13023 {

	static int N, M, answer = 0;
	static List<Integer> array[];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N];
		array = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			array[i] = new ArrayList<Integer>();
		}

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			array[a].add(b);
			array[b].add(a);
		}

		for (int i = 0; i < N; i++) {
			dfs(i, 0);
			if (answer == 1)
				break;
		}

		System.out.println(answer);

	}

	public static void dfs(int s, int cnt) {
		if (cnt == 4) {
			answer = 1;
			return;
		}

		visited[s] = true;

		for (int c = 0; c < array[s].size(); c++) {
			int now = array[s].get(c);
			if (visited[now] == false) {
				dfs(now, cnt + 1);
			}
		}
		visited[s] = false;
		return;
	}
}
