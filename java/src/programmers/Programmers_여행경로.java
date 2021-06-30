package programmers;

import java.util.*;

class Programmers_여행경로 {
    
    int N;
    String[][] Tickets;
    boolean[] used;
    ArrayList<String> answers = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        Tickets = tickets;
        used = new boolean[N];
        
        findRoute("ICN", "ICN", 0);
        
        Collections.sort(answers);
        
        return answers.get(0).split(",");
    }
    
    public void findRoute(String start, String Route, int cnt){
        if(cnt == N){
            answers.add(Route);
            return;
        }
        
        for(int i = 0; i < N; i++){
            if(!used[i] && Tickets[i][0].equals(start)){
                used[i] = true;
                findRoute(Tickets[i][1], Route + "," + Tickets[i][1], cnt + 1);
                used[i] = false;
            }
        }
    }
}