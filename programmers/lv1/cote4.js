
//크레인 인형뽑기 게임

function solution(board, moves) {

    let stack = [];
	// move - 1 열에서 순서대로 인형을 탐색
    for(const m of moves) {

        for(const b of board) {

            if(b[m-1] !== 0) {
                stack.push(b[m-1]);
                b[m-1] = 0;
                break;
            }
        }
    }

    const answer = stack.length - remove(stack).length;

    return answer
}
// 중복 지우기
function remove (stack) {

    for(let i =0; i< stack.length; i++) {
        if(stack[i] === stack[i+1]){
            stack[i] ='rm';
            stack[i+1] = 'rm';
        }
    }

    const stackCheck = stack.filter(a=>a!=='rm');
    
    for(let i =0; i<stackCheck.length; i++ ){
        if(stackCheck[i] === stackCheck[i+1]) {
            return remove(stackCheck)
        }
    }

    return stackCheck;

}
    
const board = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]];

console.log(board);
const moves = [1,5,3,5,1,2,1,4];

console.log(solution(board, moves));