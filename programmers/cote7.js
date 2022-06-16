// 멀쩡한 사각형
const w = 8
const h = 12
solution(w,h)
function solution(w, h) {
    var answer = 1;
    const gcd = getGCD(w,h);
    const box = w*h - (w+h-gcd);
    console.log(box);
    // 가로로 자르는 x 조건으로 16개 못쓰게 되었다.
    // 조건의 규칙을 구하는게 이 문제의 핵심이다.

    return answer;
}

function getGCD(num1, num2) {
    let gcd = 1;
    
    for(let i=2; i <= Math.min(num1, num2); i++) {
        if(num1 % i === 0 && num2 % i === 0) {
            gcd = i;
        }
    }

    return gcd;
}