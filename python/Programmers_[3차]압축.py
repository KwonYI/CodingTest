def solution(msg):
    answer = []
    indices = {a : i + 1 for i, a in enumerate("ABCDEFGHIJKLMNOPQRSTUVWXYZ")}
    
    index, num = 0, 27
    while index != len(msg) :
        for j in range(len(msg), index, -1) :
            if msg[index:j] in indices :
                answer.append(indices[msg[index:j]])
                indices[msg[index:j+1]] = num
                num += 1
                index = j
                break
                
    return answer