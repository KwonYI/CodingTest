def solution(lottos, win_nums):
    grade = {6 : 1, 5 : 2, 4 : 3, 3 : 4, 2 : 5, 1 : 6, 0 : 6}
    
    correct = len(set(lottos) & set(win_nums))
    zero_cnt = lottos.count(0)
    
    max_value = correct + zero_cnt
    min_value = correct
    
    return [grade[max_value], grade[min_value]]