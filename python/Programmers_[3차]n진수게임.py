def solution(n, t, m, p):
    def n_zin(num, n) :
        key = "0123456789ABCDEF"
        num, m = divmod(num, n)
        value = key[m]
        return ('' if num == 0 else n_zin(num, n)) + value
    
    original = ''
    start = 0
    while len(original) < t*m :
        original += n_zin(start, n)
        start += 1
    return original[p-1::m][:t]