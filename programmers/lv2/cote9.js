// const picks = [1, 3, 2]// [dia, iron, stone]
// const minerals = ["diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"] //순서대로 캘 수 있다.


// // 경우 수 모든 pick
// function solution(picks, minerals) {
//     let answer = 0;
//     let answerTray = [];
//     let count = 0;

//     let mp = new Map();

//     picks.forEach((elem,i) => {
//         if(i===0) {
//             mp.set( 'dia' , elem);
//         } else if(i===1) {
//             mp.set( 'iron' , elem);
//         } else if (i===2) {
//             mp.set( 'stone' , elem);
//         }
//     })

//     const mineralType = [ [ 'dia', 'iron', 'stone' ],
//                         [ 'dia', 'stone', 'iron' ],
//                         [ 'iron', 'stone', 'dia' ],
//                         [ 'iron', 'dia', 'stone' ],
//                         [ 'stone', 'dia', 'iron' ],
//                         [ 'stone', 'iron', 'dia' ] ];
  
//     mineralType.forEach((elem, idx) => {


//         let mineralsTray = [].concat(minerals);

//         elem.forEach(elem2 => {
            
//         let cntIdx =  mp.get(elem2);

//         console.log('cntIdx----------------',cntIdx);
//         console.log('mineralsTray', mineralsTray);

//         if(mineralsTray.length === 0) {
//             return
//         } else {
            
//         for(let i = 0; i < cntIdx; i++) {

//             for(let z = 0 ; z<5; z++) {

//                 if(mineralsTray.length === 0) {
//                     continue
//                 }

//                 if(elem2 === 'dia'){
//                     switch (mineralsTray[0]) {
//                         case 'diamond':
//                             answer += 1;
//                             break;
//                         case 'iron':
//                             answer += 1;
//                             break;
//                         case 'stone':
//                             answer += 1;
//                             break;
//                         default:
//                             break;
//                     }
//                 }
//                 if(elem2 === 'iron') {
//                     switch (mineralsTray[0]) {
//                         case 'diamond':
//                             answer += 5;
//                             break;
//                         case 'iron':
//                             answer += 1;
//                             break;
//                         case 'stone':
//                             answer += 1;
//                             break;
//                         default:
//                             break;
//                     }
//                 }
//                 if(elem2 === 'stone') {
//                     switch (mineralsTray[0]) {
//                         case 'diamond':
//                             answer += 25;
//                             break;
//                         case 'iron':
//                             answer += 5;

//                             break;
//                         case 'stone':
//                             answer += 1;
//                             break;
//                         default:
//                             break;
//                     }
//                 }
//                 console.log('count', count++);
//                 mineralsTray.splice(0,1);
//             }

//         }


//         }
        
//     })


    

//     answerTray.push(answer)

//     })

//     return Math.min.apply(null, answerTray)
// }

// console.log(solution(picks, minerals)); 


const picks = [1, 3, 2]// [dia, iron, stone]
const minerals = ["diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"] //순서대로 캘 수 있다.


function solution(picks, minerals) {
    const remainPicks = [...picks];
    const maxFatigue = [];
    const useable = minerals.slice(0, picks.reduce((acc, cur) => acc + cur) * 5);

    console.log('useable', useable);

    useable.forEach((mineral, index) => {
        if (index % 5 === 0) maxFatigue.push({"diamond": 0, "iron": 0, "stone": 0});

        console.log('maxFatigue', maxFatigue);
        console.log(` maxFatigue.at(-1)[${mineral}] `,  maxFatigue.at(-1)[mineral] );
        maxFatigue.at(-1)[mineral] ++;

    })

    
    maxFatigue.sort((a, b) => (b.diamond * 25 + b.iron * 5 + b.stone) - (a.diamond * 25 + a.iron * 5 + a.stone))

    console.log('maxFatigue', maxFatigue);

    const answer = maxFatigue.reduce((acc, {diamond, iron, stone}) => {
        if (remainPicks[0] !== 0) {
            remainPicks[0] --;
            return acc + diamond + iron + stone;
        } else if (remainPicks[1] !== 0) {
            remainPicks[1] --;
            return acc + diamond * 5 + iron + stone;
        } else {
            remainPicks[2] --;
            return acc + diamond * 25 + iron * 5 + stone;
        }
    }, 0)
    return answer;
}

console.log(solution(picks,minerals));

