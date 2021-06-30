package programmers;

class Programmers_단어변환 {
	int N;
	int answer = 0;
	boolean[] used;

	public int solution(String begin, String target, String[] words) {
		N = words.length;
		used = new boolean[N];

		find(begin, target, words, 0);

		return answer;
	}

	public void find(String cur, String target, String[] words, int cnt) {
		if (cur.equals(target)) {
			answer = cnt;
			return;
		}

		for (int i = 0; i < words.length; i++) {
			if (used[i])
				continue;

			if (possible(cur, words[i])) {
				used[i] = true;
				find(words[i], target, words, cnt + 1);
				used[i] = false;
			}
		}
	}

	public boolean possible(String begin, String target) {
		int diff = 0;
		for (int i = 0; i < begin.length(); i++) {
			if (begin.charAt(i) != target.charAt(i)) {
				diff++;
			}
		}
		return diff == 1;
	}
}
