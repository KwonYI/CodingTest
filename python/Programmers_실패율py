def solution(N, stages):
    rates = {}
    for stage in range(1, N+1) :
        player = stages.count(stage)
        clear = len([i for i in stages if i >= stage])
        rate = 0
        if clear != 0:
            rate = player / clear
        rates[stage] = rate
    return sorted(rates, key = lambda x : rates[x], reverse = True)