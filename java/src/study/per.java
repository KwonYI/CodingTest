package study;

import java.util.Arrays;
import java.util.Scanner;

public class per {

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
		
		permutation(0, 0);
		
		sc.close();
	}
	
	private static void permutation(int cnt, int check) {
		if(cnt == M) {
			System.out.println(Arrays.toString(outputs));
			return;
		}
		for (int i = 0; i < N; i++) {
			if((check & (1 << i)) != 0 ) continue;
			outputs[cnt] = inputs[i];
			permutation(cnt + 1, check | (1<<i));
		}
	}
}
