package Goorm;

import java.io.*;
import java.util.*;

class 징검다리 {
	
	static int d, n, m;
	static int[] stones, selected;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		d = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		stones = new int[n];
		selected = new int[n - m];
		
		for(int i = 0; i < n; i++){
			stones[i] = Integer.valueOf(bf.readLine());
		}
		
		Arrays.sort(stones);
		play(0, 0);
		
		System.out.println(answer);
	}
	
	public static void play(int cnt, int cur){
		if(cnt == n - m){
			int minStep = selected[0];
			for(int i = 0; i < n - m - 1; i++){
				minStep = Math.min(minStep, selected[i + 1] - selected[i]);
			}
			minStep = Math.min(minStep, d - selected[n - m - 1]);
			answer = Math.max(minStep, answer);
			return;
		}
		for(int i = cur; i < n; i++){
			selected[cnt] = stones[i];
			play(cnt + 1, i + 1);
		}
	}
}