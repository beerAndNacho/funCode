// 124 나라의 숫자
function solution(n) {

    var answer = '';

    // 3으로 나눴을 때 나머지가 0이면 4, 1이면 1, 2이면 2
    const fot = ['4','1','2'];

    while (n>0) {
        // 3으로 나누면 나머지 값은 3미만의 값이 나온다(핵심)
        const remainder = n % 3;
        console.log(remainder);
        answer = fot[remainder] + answer;

        console.log(n);
        // n이3일 때 아래 식이 n-1으로 해야 나머지가 0일 때 한 자리 수가 나온다.
        n = Math.floor((n-1)/3);

    }

    return answer;
}

console.log(solution(6));