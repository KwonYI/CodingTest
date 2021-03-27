package study;

import java.util.Arrays;
import java.util.Scanner;

public class comb_du {

	static int N, M;
	static int[] inputs, outputs;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		inputs = new int[N];
		outputs = new int[M];
		
		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}
		
		comb(0, 0);
		
		sc.close();
	}
	private static void comb(int cnt, int cur) {
		if(cnt == M) {
			System.out.println(Arrays.toString(outputs));
			return;
		}
		for (int i = cur; i < N; i++) {
			outputs[cnt] = inputs[i];
			comb(cnt + 1, i);
		}
	}
}
