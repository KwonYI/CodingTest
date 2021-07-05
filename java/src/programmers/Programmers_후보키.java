package programmers;

import java.util.*;

class Programmers_후보키 {
    
    int columnCnt;
    String[][] Relations;
    ArrayList<Integer> candidates = new ArrayList<>();
    
    public int solution(String[][] relation) {
        Relations = relation;
        columnCnt = relation[0].length;
        
        for(int key = 1; key < (1 << columnCnt); key++){
            if(isMin(key) && isUnique(key)){
                candidates.add(key);
            }
        }
        
        return candidates.size();
    }
    
    public boolean isMin(int key){
        for(int prev : candidates){
            if((prev & key) == prev) return false;          
        }
        return true;
    }
    
    public boolean isUnique(int key){
        HashSet<String> set = new HashSet<>();
        
        for(String[] row : Relations){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 8; i++){
                if( (key & (1 << i)) != 0 ) sb.append(row[i]).append(' ');
            }
            
            if(!set.add(sb.toString())) return false;
        }
        
        return true;
    }
}