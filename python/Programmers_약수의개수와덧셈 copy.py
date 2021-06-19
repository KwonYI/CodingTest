# 제곱수는 약수의 개수가 홀수
def solution(left, right):
    answer = 0
    for num in range(left, right + 1) :
        if func(num) % 2 == 0 :
            answer += num
        else :
            answer -= num
    return answer

def func(num) :
    cnt = 1
    for i in range(1, num // 2 + 1) :
        if num % i == 0 :
            cnt += 1
    return cnt