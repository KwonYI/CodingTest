def solution(skill, skill_trees):
    answer = 0
    for skills in skill_trees :
        sub = []
        for s in skill :
            try : 
                sub.append(skills.index(s))
            except :
                sub.append(99)
        if sub == sorted(sub) :
            answer += 1
    return answer
