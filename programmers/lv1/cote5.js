//카운트 다운

function solution(start, end) {
    let answer =[];

    if(start === end) {
        answer.push(start);
    }

    if(start < end) {

        for(let i = start; i <= end; i ++) {
    
            answer.push(i)
        }
    } else {
        for(let i = start; i >= end; i --) {
    
            answer.push(i)
        }
    }
 
    return answer;
}

const result = solution(10, 3);
