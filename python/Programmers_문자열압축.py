def solution(s):
    answer = len(s)
    
    for i in range(1, len(s) // 2 + 1) :
        new_s = [s[j:j+i] for j in range(0, len(s), i)]
        
        cnt = 1
        prev = []
        cnts = []
        
        while new_s :
            cur = new_s.pop()
            
            if [cur] != new_s[-1:] :
                prev.append(cur)
                cnts.append(str(cnt))
                cnt = 1
            else :
                cnt += 1
                
        total = 0
        for p, c in zip(prev, cnts) :
            total += len(p)
            if int(c) != 1 :
                total += len(c)
                
        answer = min(answer, total)
                
    return answer
