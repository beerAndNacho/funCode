// 타겟 넘버

const numbers = [4, 1, 2, 1];
const target = 4;
console.log(solution(numbers, target));

function solution(numbers, target) {
    var answer  = 0;

    dfs(0,0);

    function dfs(level, sum) {
        if(level === numbers.length) {
            if(sum === target) {
                answer += 1;
            }
            return;
        }

   
        dfs(level + 1 , sum + numbers[level]);
        dfs(level + 1, sum - numbers[level]);
    }
  
    return answer;
}

