package programmers;

import java.util.*;

class Programmers_네트워크 {
    
    int N;
    boolean[] visited;
    int[][] Computers;
    
    public int solution(int n, int[][] computers) {
        N = n;
        Computers = computers;
        
        int answer = 0;
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(!visited[i] && computers[i][j] == 1){
                    answer++;
                    bfs(i);
                }
            }
        }
        
        return answer;
    }
    
    public void bfs(int i){
        Queue<Integer> q = new LinkedList<>();
        
        q.add(i);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            if(visited[cur]) continue;
            visited[cur] = true;
            
            for(int j = 0; j < N; j++){
                if(!visited[j] && Computers[cur][j] == 1){
                    q.add(j);
                }
            }
        }
    }
}