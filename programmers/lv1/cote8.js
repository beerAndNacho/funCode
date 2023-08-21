const t = "10203"
const p = "15"
function solution(t, p) {
    let answer = 0;

    let pSize = p.length;
    let tSplit = t.split('');
    let checkTray = [];
    let collectTray = [];


    for (let i = 0; i < tSplit.length; i++) {
        checkTray.push(tSplit[i]);

        if (checkTray.length === pSize) {
            collectTray.push(checkTray.join(''));
            i -= pSize-1;
            checkTray = [];
        }
        
    }

    collectTray.forEach(elem => {
        if(Number(elem) <= p) {
            answer++;
        }
    })
console.log(answer);
    return answer;
}

solution(t, p)