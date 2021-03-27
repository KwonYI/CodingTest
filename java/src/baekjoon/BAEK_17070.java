package baekjoon;

import java.io.*;
import java.util.*;
 
public class BAEK_17070 {
	
    static int N;
    static int[][] map;
    static int total;
 
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        N = Integer.valueOf(bf.readLine());
        map = new int[N + 1][N + 1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        total = 0;
        solve(1, 2, 0);
 
        System.out.println(total);
    }
 
    public static void solve(int r, int c, int d) {
        if (r == N && c == N) {
            total++;
            return;
        }
 
        switch (d) {
        case 0:
            if (c + 1 <= N && map[r][c + 1] == 0) {
                solve(r, c + 1, 0);
            }
            break;
        case 1:
            if (r + 1 <= N && map[r + 1][c] == 0) {
                solve(r + 1, c, 1);
            }
            break;
        case 2:
            if (c + 1 <= N && map[r][c + 1] == 0) {
                solve(r, c + 1, 0);
            }
 
            if (r + 1 <= N && map[r + 1][c] == 0) {
                solve(r + 1, c, 1);
            }
            break;
        }
 
        if (c + 1 <= N && r + 1 <= N && map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) {
            solve(r + 1, c + 1, 2);
        }
    }
}