def solution(numbers, hand):
    L = '*'
    R = '#'
    keypad = dict()
    number = '123456789*0#'
    for r in range(4) :
        for c in range(3) :
            keypad[number[r*3 + c]] = [r, c]
            
    answer = ''
    for number in numbers :
        if number in [1, 4, 7] :
            answer += 'L'
            L = str(number)
        elif number in [3, 6, 9] :
            answer += 'R'
            R = str(number)
        else :
            r_dist = dist(R, str(number), keypad)
            l_dist = dist(L, str(number), keypad)
            
            if r_dist > l_dist :
                answer += 'L'
                L = str(number)
            elif r_dist < l_dist :
                answer += 'R'
                R = str(number)
            else :
                if hand == 'right' :
                    answer += 'R'
                    R = str(number)
                else :
                    answer += 'L'
                    L = str(number)
    return answer

def dist(R, T, keypad) :
    return abs(keypad[R][0] - keypad[T][0]) + abs(keypad[R][1] - keypad[T][1])