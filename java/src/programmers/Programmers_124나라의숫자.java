package programmers;

class Programmers_124나라의숫자 {
	
	class Solution {

	    char[] myZin = {'4', '1', '2'};
	    
	    public String solution(int n) {
	        StringBuilder answer = new StringBuilder();
	        
	        while( n != 0){
	            int m = n % 3;
	            n = (n - 1) / 3;
	            answer.insert(0, myZin[m]);                             
	        }
	        
	        return answer.toString();
	    }
	}
}
