def solution(n, words):
    prev = [words[0]]
    turn, index = -1, -1
    
    for word in words[1:] :
        if prev[-1][-1] != word[0] or word in prev :
            turn, index = divmod(len(prev), n)
            break
        else :
            prev.append(word)

    return [index + 1, turn + 1]