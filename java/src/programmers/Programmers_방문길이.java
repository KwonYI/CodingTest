package programmers;

class Programmers_방문길이 {

	int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	boolean[][][][] visited = new boolean[11][11][11][11];

	public int solution(String dirs) {
		int R = 5;
		int C = 5;
		int D = -1;
		int answer = 0;

		for (int i = 0; i < dirs.length(); i++) {
			char order = dirs.charAt(i);

			switch (order) {
				case 'U':
					D = 0;
					break;
				case 'D':
					D = 1;
					break;
				case 'L':
					D = 2;
					break;
				case 'R':
					D = 3;
					break;
			}

			int nr = R + dir[D][0];
			int nc = C + dir[D][1];

			if (nr < 0 || nc < 0 || nr > 10 || nc > 10) {
				continue;
			}

			if (!visited[R][C][nr][nc] && !visited[nr][nc][R][C]) {
				visited[nr][nc][R][C] = visited[R][C][nr][nc] = true;
				answer += 1;
			}

			R = nr;
			C = nc;
		}

		return answer;
	}
}