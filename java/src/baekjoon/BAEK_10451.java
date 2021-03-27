package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_10451 {
	
	static int T, N;
	static int[] p;
	static boolean[] visited;
	static int cycleCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.valueOf(bf.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.valueOf(bf.readLine());
			
			cycleCnt = 0;
			p = new int[N + 1];
			visited = new boolean[N + 1];
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 1; i <= N; i++) {
				p[i] = Integer.parseInt(st.nextToken());
			}
			
			find(1, 1);
			
			sb.append(cycleCnt).append('\n');
		}
		
		System.out.println(sb);
	}

	private static void find(int start, int target) {
		visited[start] = true;
		
		int next = p[start];
		if(next == target) {
			cycleCnt++;
			for (int i = 1; i <= N; i++) {
				if(!visited[i]) {
					find(i, i);
				}
			}
		}else {
			find(next, target);
		}
	}
}
