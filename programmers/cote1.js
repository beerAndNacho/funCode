const id_list   = ["muzi", "frodo", "apeach", "neo"]
let report      = ["muzi frodo","muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]
const k         = 2

//  코딩테스트 연습2022 KAKAO BLIND RECRUITMENT신고 결과 받기

function solution(id_list, report, k) {

    const cote1 = {

    newReport : (report) => {
        
        const reportInSet = new Set(report);

        // Set {
        //     'muzi frodo',
        //     'apeach frodo',
        //     'frodo neo',
        //     'muzi neo',
        //     'apeach muzi'
        //   }


        const reportInMap = [...reportInSet].map(elem => {
            return elem.split(' ');
        })

        // [
        //     [ 'muzi', 'frodo' ],
        //     [ 'apeach', 'frodo' ],
        //     [ 'frodo', 'neo' ],
        //     [ 'muzi', 'neo' ],
        //     [ 'apeach', 'muzi' ]
        //   ]
          
        return reportInMap;
    },


    userReported : (newReport) => {

        let userReportedCnt = new Map();

        for(const userReported of newReport) {
            userReportedCnt.set(userReported[1], userReportedCnt.get(userReported[1])+1 || 1);
        }

        return userReportedCnt;
    },

    userReporter : (newReport, userReported, k) => {

        let userReporter = new Map();

        for(const reporter of newReport) {

            if(userReported.get(reporter[1]) >= k) {
                userReporter.set(reporter[0], userReporter.get(reporter[0]) +1 || 1);
            }

        }

        return userReporter;
    },

    answer : (id_list, userReporter) => {
        
        return id_list.map(elem => {
            return userReporter.get(elem) || 0
        })
    }

}

const newReport = cote1.newReport(report)
const userReportedCnt = cote1.userReported(newReport);
const userReporter = cote1.userReporter(newReport, userReportedCnt, k);
const answer = cote1.answer(id_list, userReporter);
    
return answer;

}




//이전 풀이

// const id_list = ["muzi", "frodo", "apeach", "neo"]
// let report =["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]
// const k = 2


// let id_list_cnt = [];
// let result = []
// let result2 = []

// id_list.forEach(() => {
//     id_list_cnt.push(0)
// })

// report.forEach(elem => {

//     if(!result.includes(elem)) {
//         result.push(elem)
//     }
// })

// result.forEach(elem => {

//     const em = elem.split(' ');

//     console.log(em);


//     result2.push(em[1])
// })

// result.forEach(elem => {
//    let elem2 = elem.split(' ');

//    for(let i=0; i< id_list.length; i++ ){
//        if(elem2[0] === id_list[i]) {


//         let count = result2.filter(elem => elem2[1] === elem).length;

//         if(count >= k) {
//             id_list_cnt[i] = id_list_cnt[i] + 1;
//         }

//        }
//    }

// })

// console.log(id_list_cnt);











