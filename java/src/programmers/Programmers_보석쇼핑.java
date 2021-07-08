import java.util.*;

class Solution {
    
    Queue<String> prevs = new LinkedList<>();
    HashSet<String> allGem = new HashSet<>();
    HashMap<String, Integer> my = new HashMap<>();
    
    public int[] solution(String[] gems) {
        for(String gem : gems) {
            allGem.add(gem);
        }
        
        int left = 0;
        int index = 0;
        int length = Integer.MAX_VALUE;
        int N = gems.length;
        int M = allGem.size();
        
        for(int i = 0; i < N; i++){
            String cur = gems[i];
            prevs.add(cur);
            my.put(cur, my.getOrDefault(cur, 0) + 1);
            
            while(true){
                String prev = prevs.peek();
                
                if(my.get(prev) > 1){
                    index++;
                    prevs.poll();
                    my.put(prev, my.get(prev) - 1);
                }else{
                    break;
                }
            }
            
            if(M == my.size() && length > prevs.size() ) {
                left = index;
                length = prevs.size();
            }
        }
        
        return new int[] { left + 1, left + length};
    }
}