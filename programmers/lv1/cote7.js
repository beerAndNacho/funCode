// 개인정보 수집 유효기간

const today = "2022.05.19"
const terms = ["A 6", "B 12", "C 3"]
const privacies = ["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]


const mp = new Map;

terms.forEach(element=>{
    mp.set(element.split(' ')[0], element.split(' ')[1])
})


function solution(today, terms, privacies) {
    var answer = [];

    const eventToday = new Date(today);

    console.log('eventToday' , eventToday);

    privacies.forEach((dt, index) => {

        const getDate = new Date(dt.split(' ')[0]);

        const ruleMonth = Number(mp.get(dt.split(' ')[1]));

        const result = new Date(getDate.getFullYear(), getDate.getMonth()+ruleMonth, getDate.getDate());

        console.log(result);


        if(eventToday >= result) {
            answer.push(index+1);
        }


    })


    return answer;
}


console.log(solution(today, terms, privacies));



