package swea;

import java.io.*;
import java.util.*;

public class SWEA_7650 {

	static class Person implements Comparable<Person>{
		int index;
		int time;

		public Person(int index, int time) {
			this.index = index;
			this.time = time;
		}
		
		public int compareTo(Person o) {
			return this.time - o.time;
		}
	}

	static int N;
	static int time;
	static PriorityQueue<Person> people = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.valueOf(bf.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.valueOf(bf.readLine());
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int index = 1; index <= N; index++) {
				people.add(new Person(index, Integer.parseInt(st.nextToken())));
			}
			
			time = 0; // 전체 시간
			int curTime = 0; // 해당 사람이 뽑는데 걸리는 시간
			while(!people.isEmpty()) {
				curTime += people.poll().time;
				time += curTime;
			}
			
			sb.append('#').append(testCase).append(' ').append(time).append('\n');
		}
		
		System.out.println(sb.toString());
	}
}