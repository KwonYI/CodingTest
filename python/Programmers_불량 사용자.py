from itertools import permutations

def solution(user_id, banned_id):
    answer = []
    for permutation in permutations(user_id, len(banned_id)) :
        if matching(permutation, banned_id) :
            permutation = set(permutation)
            if permutation not in answer :
                answer.append(permutation)
        
    return len(answer)

def matching(users, bans) :
    for user, ban in zip(users, bans) :
        if len(user) != len(ban) or not check(user, ban) :
            return False
    return True
    

def check(user, ban) :
    for u, b in zip(user, ban) :
        if b == '*' :
            continue;
        elif u != b :
            return False
    return True