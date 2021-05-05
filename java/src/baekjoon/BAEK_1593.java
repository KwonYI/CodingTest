package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1593 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int g = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		String W = bf.readLine();
		String S = bf.readLine();

		int[] wCnts = new int[52];
		int[] sCnts = new int[52];

		for (int i = 0; i < g; i++) {
			char x = W.charAt(i);
			char y = S.charAt(i);

			if (x - 'a' < 0) {
				int index = x - 'A';
				wCnts[index + 26]++;
			} else {
				int index = x - 'a';
				wCnts[index]++;
			}

			if (y - 'a' < 0) {
				int index = y - 'A';
				sCnts[index + 26]++;
			} else {
				int index = y - 'a';
				sCnts[index]++;
			}
		}

		int cnt = 0;
		if (same(sCnts, wCnts)) {
			cnt++;
		}
		
		for (int i = g; i < s; i++) {
			char next = S.charAt(i);
			char prev = S.charAt(i - g);
			
			if (next - 'a' < 0) {
				int index = next - 'A';
				sCnts[index + 26]++;
			} else {
				int index = next - 'a';
				sCnts[index]++;
			}

			if (prev - 'a' < 0) {
				int index = prev - 'A';
				sCnts[index + 26]--;
			} else {
				int index = prev - 'a';
				sCnts[index]--;
			}
			
			if (same(sCnts, wCnts)) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	public static boolean same(int[] x, int[] y) {
		for (int i = 0; i < 52; i++) {
			if (x[i] != y[i]) {
				return false;
			}
		}
		return true;
	}
}