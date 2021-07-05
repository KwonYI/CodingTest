def solution(p):
    if is_proper(p) : return p
    return convert(p)

def is_proper(p) :
    cnt = 0;
    for c in p :
        if c == '(' : 
            cnt += 1
        else :
            if cnt == 0 : 
                return False
            else : 
                cnt -= 1
    return cnt == 0

def convert(p) :
    if p == '' :
        return p
    else :
        u, v = split(p)
        
        if is_proper(u) :
            return u + convert(v)
        else :
            new_str = '(' + convert(v) + ')'
            for s in u[1:-1] :
                if s == ')' :
                    new_str += '('
                else :
                    new_str += ')'
                    
            return new_str
        
def split(p) :
    o_cnt, c_cnt = 0, 0;
    for c in p :
        if c == '(' :
            o_cnt += 1
        else :
            c_cnt += 1
        
        if o_cnt == c_cnt : break
            
    return p[:o_cnt + c_cnt], p[o_cnt + c_cnt:]