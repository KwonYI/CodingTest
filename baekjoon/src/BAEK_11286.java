import java.io.*;
import java.util.*;

public class BAEK_11286 {
	
	static int N;
	static HashMap<Integer, Integer> map = new HashMap<>(); // 원래 입력값 가지는 맵
	static TreeMap<Integer, Integer> absMap = new TreeMap<>(); // 절댓값 가지는 맵

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.valueOf(bf.readLine());
		
		for (int i = 0; i < N; i++) {
			int cur = Integer.valueOf(bf.readLine());
			
			if(cur == 0) {
				if(absMap.isEmpty()) sb.append(0).append('\n');
				else {
					int key = absMap.firstKey(); // 항상 양수
					int minus = -key;
					
					if(map.containsKey(minus)) {
						sb.append(minus).append('\n');
						
						if(map.get(minus) == 1) map.remove(minus);
						else map.put(minus, map.get(minus) - 1);
					}
					else {
						sb.append(key).append('\n');

						if(map.get(key) == 1) map.remove(key);
						else map.put(key, map.get(key) - 1);
					}

					if(absMap.get(key) == 1) absMap.remove(key);
					else absMap.put(key, absMap.get(key) - 1);
				}
			}else {
				int abs = Math.abs(cur);

				absMap.put(abs, absMap.getOrDefault(abs, 0) + 1); // key가 있으면 value, 없으면 0
				map.put(cur, map.getOrDefault(cur, 0) + 1); 
			}
		}
		
		System.out.println(sb);
	}
}