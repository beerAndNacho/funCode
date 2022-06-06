const lottos   = [44, 1, 0, 0, 31, 25]
let win_nums      = [31, 10, 45, 1, 6, 19]

console.log(solution(lottos, win_nums));
// 로또의 최고 순위와 최저 순위

function solution(lottos, win_nums) {

    var answer = [];
    
    let zero = lottos.filter(elem => {
        if(elem===0) {
            console.log(elem);
            return '0'
        }
    });
    console.log('zero', zero);
    const matching = lottos.filter(elem => {
        for(const num of win_nums) {
            if(elem===num) {
                return elem
            }
        }
    })
    let lowest = matching.length;
    let highest = lowest+zero.length;
    
    
    console.log('highest', highest);
    
    switch (highest) {
        case 0:
        highest = 6
        break;
        case 1:
        highest = 6
        break;
        case 2:
        highest = 5
        break;
        case 3:
        highest = 4
        break;
        case 4:
        highest = 3
        break;
        case 5:
        highest = 2
        break;
        case 6:
        highest = 1
        break;
    }
    console.log('highest1', highest);
    console.log('lowest', lowest);
    switch (lowest) {
        case 0:
        lowest = 6
            break;
        case 1:
        lowest = 6
            break;
        case 2:
        lowest = 5
            break;
        case 3:
        lowest = 4
            break;
        case 4:
        lowest = 3
            break;
        case 5:
        lowest = 2
            break;
        case 6:
        lowest = 1
            break;
    }
    
    return [highest, lowest];
}