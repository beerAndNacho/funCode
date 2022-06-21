// 기능개발

const progresses =[93, 30, 55]
const speeds = 	[1, 30, 5]

solution(progresses, speeds);

function solution(progresses, speeds) {
    var answer = [];

    const originPush =[];
    
    const check = (prg, sped, cnt) => {

        if(cnt !==0 ){
            prg =prg+sped*cnt;
            if(prg >= 100) {
                originPush.push(cnt)
                return cnt;
            }
        }

        while(true) {
            ++cnt;
            prg = prg+sped
            if(prg >= 100) {
                originPush.push(cnt)
                break;
            }
        }
        
        return cnt;

    }

    let cnt = 0;
    
    progresses.forEach((elem,index) => {
           
        cnt = check(elem,speeds[index],cnt);
   
        answer.push(cnt)

       })

     let temp = [];
     
     for(let i = 0; i < answer.length; i++){
         let count = 1;
         for(let z = i+1; z < answer.length; z++){
             if(answer[i] === answer[z]) {
                 count++;
                 i++;
            }else{
                 break;
             }
         }

         temp.push(count);

     }

    return temp;
}