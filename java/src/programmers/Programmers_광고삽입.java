package programmers;

class Programmers_광고삽입 {
    
    public int ttos(String str){
        String[] arr = str.split(":");
        return Integer.valueOf(arr[0]) * 60 * 60 
            + Integer.valueOf(arr[1]) * 60 
            + Integer.valueOf(arr[2]);
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int totalTime = ttos(play_time);
        int advTime = ttos(adv_time);
        int[] times = new int[totalTime];
        
        for(String log : logs){
            String[] log_arr = log.split("-");
            int start_time = ttos(log_arr[0]);
            int end_time = ttos(log_arr[1]);
            
            for(int i = start_time; i < end_time; i++){
                times[i]++;
            }
        }
        
        long curView = 0;
        for(int i = 0; i < advTime; i++){
            curView += times[i];
        }
        
        long maxView = curView;
        int maxViewIndex = 0;
        for(int i = advTime; i < totalTime; i++){
            curView = curView - times[i - advTime] + times[i];
            
            if(curView > maxView){
                maxViewIndex = i - advTime + 1;
                maxView = curView;
            }
        }
        
        return String.format("%02d:%02d:%02d", maxViewIndex / 3600, maxViewIndex / 60 % 60, maxViewIndex % 60);
    }
}