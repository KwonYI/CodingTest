package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_11054 {

	static int N;
	static int[] arr;
	static int[][] p;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		arr = new int[N + 1];
		p = new int[2][N + 1];

		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
            p[0][i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) {
                    p[0][i] = Math.max(p[0][j] + 1, p[0][i]);
                }
            }
        }
		
		for (int i = N; i >= 1; i--) {
            p[1][i] = 1;
            for (int j = N; j > i; j--) {
                if (arr[i] > arr[j]) {
                    p[1][i] = Math.max(p[1][j] + 1, p[1][i]);
                }
            }
        }
		
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = Math.max(p[0][i] + p[1][i] - 1, answer);
		}
		
		System.out.println(answer);
	}
}