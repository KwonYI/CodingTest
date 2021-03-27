package study;

import java.util.Arrays;
import java.util.Scanner;

public class comb {

	static int n, m;
	static int[] inputs, outputs;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		inputs = new int[n];
		outputs = new int[m];
		
		for (int i = 0; i < n; i++) {
			inputs[i] = sc.nextInt();
		}
		
		combination(0, 0);
		
		sc.close();
	}
	private static void combination(int cnt, int cur) {
		if(cnt == m) {
			System.out.println(Arrays.toString(outputs));
			return;
		}
		for (int i = cur; i < n; i++) {
			outputs[cnt] = inputs[i];
			combination(cnt + 1, i + 1);
		}
	}
}
