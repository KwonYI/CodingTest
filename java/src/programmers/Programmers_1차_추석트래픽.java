package programmers;

import java.util.*;

class Programmers_1차_추석트래픽 {
    
    class Time{
        float start;
        float end;
        
        public Time(float s, float e){
            this.start = s;
            this.end = e;
        }
    }
    
    ArrayList<Time> arr = new ArrayList<>();
    
    public int solution(String[] lines) {
        int answer = 0;
        for(String line : lines){
            Time time = convert(line);
            arr.add(time);
        }
        
        for(Time time : arr){
            int sCnt = 0, eCnt = 0;
            float start = time.start;
            float end = time.end;
            
            for(Time subtime : arr){
                if(inTime(subtime, start)){
                    sCnt++;
                }
                
                if(inTime(subtime, end)){
                    eCnt++;
                }
            }
            
            answer = Math.max(Math.max(sCnt, eCnt), answer);
        }
        
        return answer;
    }
    
    public boolean inTime(Time time, float check){
        float start = check;
        float end = start + 1;
        
        return time.start < end && time.end >= start;
    }
    
    public Time convert(String line){
        String[] arr = line.split(" ");
        String[] timeArr = arr[1].split(":");
        
        float end = Integer.valueOf(timeArr[0]) * 60 * 60 
            + Integer.valueOf(timeArr[1]) * 60
            + Float.valueOf(timeArr[2]);
        
        float during = Float.valueOf(arr[2].substring(0, arr[2].length() - 1));
        
        return new Time(end - during + 0.001F, end);
    }
}