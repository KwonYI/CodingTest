
def solution(s):
    s = s[2:-2].split("},{")
    s.sort(key = lambda x : len(x))
    answer = []
    
    for ele in s :
        ele = ele.split(",")
        for i in ele :
            if int(i) not in answer :
                answer.append(int(i))
    
    return answer