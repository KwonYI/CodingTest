def solution(answers):
    patterns = [ 
                 [1, 2, 3, 4, 5],
                 [2, 1, 2, 3, 2, 4, 2, 5],
                 [3, 3, 1, 1, 2, 2, 4, 4, 5, 5] 
               ]
    
    scores = [0, 0, 0]
    lens = [len(pattern) for pattern in patterns]
    
    for index in range(len(answers)) :
        cur_answer = answers[index]
        
        for i in range(len(patterns)) :
            my = patterns[i][index % lens[i]]
            if cur_answer == my : 
                scores[i] += 1
                
    max_score = max(scores)
    
    answer = []
    for index in range(len(scores)) :
        if scores[index] == max_score : answer.append(index + 1)  
            
    return answer