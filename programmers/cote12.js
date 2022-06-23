// /주차 요금 계산
const fees =[1, 461, 1, 10]
const records = ["00:00 1234 IN"]

console.log(solution(fees, records));

function solution(fees, records) {
    var answer = [];
    let newSet = new Set();
    let carRecord = new Map();

    const recordArr = records.map(record => {

        const arr = record.split(' ');

        newSet.add(arr[1])

        return arr
    })


    newSet = newSet;

    [...newSet].map(carNum=>{

        let countTime = 0;
        let oddYn = 0;

        while(true) {

            recordArr.forEach((arr,index,origin) => {
                if(arr[1] === carNum) {
                    ++oddYn;
                    if(arr[2] === 'IN') {

                        const time = arr[0].split(":");
                        const hour = time[0];
                        const min = time[1];

                        const totalMin = parseInt(hour)*60 + parseInt(min);

                        countTime -= totalMin;

                    }else{

                        const time = arr[0].split(":");
                        const hour = time[0];
                        const min = time[1];

                        const totalMin = parseInt(hour)*60 + parseInt(min);

                        countTime += totalMin;

                    }

                }
                if(index===recordArr.length-1){

                    if(oddYn%2===1){
                        countTime += 23*60+59;
                    }
                }
            })
            carRecord.set(carNum, countTime);

            break;
        }

        answer =[...newSet].sort().map(carNum => {

            if(carRecord.get(carNum) <= fees[0]) return fees[1]

            return  fees[1]+Math.ceil((carRecord.get(carNum)-fees[0])/fees[2]) * fees[3]
        })

    })

    return answer;
}