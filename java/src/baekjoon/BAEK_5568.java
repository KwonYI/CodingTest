package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_5568 {

	static int n, k;
	static int[] cards;
	static int[] myCards;
	static HashSet<Integer> myNum = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.valueOf(bf.readLine());
		k = Integer.valueOf(bf.readLine());

		cards = new int[n];
		myCards = new int[k];

		for (int i = 0; i < n; i++) {
			cards[i] = Integer.valueOf(bf.readLine());
		}
		
		makeNum(0, 0);

		System.out.println(myNum.size());
	}
	
	public static void makeNum(int cnt, int flag) {
		if(cnt == k) {
			StringBuilder sb = new StringBuilder();
			for(int i : myCards) {
				sb.append(i);
			}
			myNum.add(Integer.valueOf(sb.toString()));
			return;
		}
		for(int i = 0; i < n; i++) {
			if((flag & (1 << i )) != 0) continue;
			myCards[cnt] = cards[i];
			makeNum(cnt + 1, flag | (1 << i));
		}
	}
}