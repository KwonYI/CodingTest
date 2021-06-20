def solution(record) :
    id_dict = dict()
    sub = []
    answer = []
    for text in record :
        text = text.split(" ")
        sub.append([text[0], text[1]])
        if text[0] in ["Enter", "Change"] :
            id_dict[text[1]] = text[2]
            
    for command, user in sub :
        if command == "Enter" :
            answer.append(id_dict[user] + "님이 들어왔습니다.")
        elif command == "Leave" :
            answer.append(id_dict[user] + "님이 나갔습니다.")
        else :
            pass
    return answer