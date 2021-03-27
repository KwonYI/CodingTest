package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Dijkstra {

	static int N;
	static final int INF = Integer.MAX_VALUE;

	static int[][] arr;
	static int[] d;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		arr = new int[N][N];
		d = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}

			d[i] = INF;
		}

		int start = 0;
		int end = N - 1;
		
		d[start] = 0;
		
		int min, cur;

		for (int i = 0; i < N; i++) {
			
			min = INF;
			cur = 0;

			for (int j = 0; j < N; j++) {
				if(min > d[j]) {
					min = d[j];
					cur = j;
				}
			}
			
			if(cur == end) break;

			for (int j = 0; j < N; j++) {
				if(arr[cur][j] != 0 && d[j] > d[cur] + arr[cur][j]) {
					d[j] = d[cur] + arr[cur][j];
				}
			}
		}
		
		System.out.println(d[end]);

	}
}