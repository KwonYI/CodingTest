package programmers;

import java.util.*;

class Programmers_순위검색 {
    
    HashMap<String, ArrayList<Integer>> allCase = new HashMap<>();
    
    public int[] solution(String[] infos, String[] query) {
        for(String info : infos){
            String[] arr = info.split(" ");
            int score = Integer.valueOf(arr[4]);
            
            for(int i = 0; i < (1 << 4); i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < 4; j++){
                    if( (i & (1 << j)) != 0 ) sb.append('-');
                    else sb.append(arr[j]);
                }

                if(!allCase.containsKey(sb.toString())){
                    allCase.put(sb.toString(), new ArrayList<>());
                }

                allCase.get(sb.toString()).add(score);
            }
        }
        
        for(String key : allCase.keySet()){
            Collections.sort(allCase.get(key));
        }
        
        int[] answer = new int[query.length];
        for(int i = 0; i < query.length; i++){
            String[] qArr = query[i].split(" ");
            String q = qArr[0] + qArr[2] + qArr[4] + qArr[6];
            int score = Integer.valueOf(qArr[7]);
            
            answer[i] = binarySearch(q, score);
        }
        
        return answer;
    }
    
    public int binarySearch(String q, int score){
        if(allCase.containsKey(q)){
            ArrayList<Integer> arr = allCase.get(q);
            int left = 0, right = arr.size() - 1;
            while(left <= right){
                int mid = (left + right) / 2;
                
                if(arr.get(mid) >= score){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            return arr.size() - left;
        }else{
            return 0;
        }
    }
}