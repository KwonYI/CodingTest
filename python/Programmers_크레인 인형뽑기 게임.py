def solution(board, moves):
    my_basket = []
    answer = 0
    
    for move in moves :
        index = move - 1
        for row in range(len(board)) :
            if board[row][index] != 0 :
                if my_basket[-1:] == [board[row][index]] :
                    my_basket.pop()
                    answer = answer + 2
                else :
                    my_basket.append(board[row][index])
                board[row][index] = 0                
                break;
    return answer