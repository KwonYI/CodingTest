package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_10814 {

	static class Name implements Comparable<Name> {
		String name;
		int age;
		int index;

		public Name(String name, int age, int index) {
			this.name = name;
			this.age = age;
			this.index = index;
		}

		public int compareTo(Name o) {
			if (this.age == o.age) {
				return this.index - o.index;
			} else {
				return this.age - o.age;
			}
		}
	}

	static int N;
	static PriorityQueue<Name> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		for (int i = 0; i < N; i++) {
			String[] inputs = bf.readLine().split(" ");

			int age = Integer.valueOf(inputs[0]);
			String name = inputs[1];

			pq.add(new Name(name, age, i));
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Name cur = pq.poll();

			sb.append(cur.age).append(' ').append(cur.name).append('\n');
		}

		System.out.println(sb);
	}
}