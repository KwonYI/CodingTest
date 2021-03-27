package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_14719 {

	static int N, M;
	static int[] map;
	static int total;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M];

		int maxIndex = 0; // 가장 높은 블록 인덱스 찾기
		int maxHeight = 0;

		st = new StringTokenizer(bf.readLine());
		for (int r = 0; r < M; r++) {
			int height = Integer.parseInt(st.nextToken());
			if (height >= maxHeight) {
				maxHeight = height;
				maxIndex = r;
			}
			map[r] = height;
		}

		maxHeight = map[0]; // 가장 높이 쌓인 블록 저장, 초기값은 가장 왼쪽
		for (int i = 1; i < maxIndex; i++) {
			if (map[i] < maxHeight) total += (maxHeight - map[i]);
			else 	maxHeight = map[i];
		}

		maxHeight = map[M - 1]; // 가장 높이 쌓은 블록 저장, 가장 오른쪽 값으로 초기화
		for (int i = M - 2; i > maxIndex; i--) {
			if (map[i] < maxHeight) total += (maxHeight - map[i]);
			else maxHeight = map[i];
		}

		System.out.println(total);
	}
}