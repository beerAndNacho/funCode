// const tempArray = Array.from("BADCE");

// tempFunc(tempArray,2);

// function tempFunc(tempArray, num) {

//     tempArray= tempArray.sort();

//     if(num ===1) return tempArray.map(elem => [elem]);

//     tempArray.forEach((fixed, index, origin) => {

//         const restCombination = tempFunc(origin.slice(index+1), num -1);
        
//         const result = restCombination.map(combination => [fixed, ...combination])

//         console.log(result);

//     })

// }


// 메뉴 리뉴얼
const orders = ["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"];
const course = [2,3,5];

solution(orders,course);

// 이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로함
// 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성하려고한다.
// 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리에 포함한다.
// 만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담아 return 하면 됩니다.

function solution(orders, course) {

    // 각 코스 원소 값을 기준으로
    // 기존에 주문된 메뉴에서 새로운 구성을
    // 코스의 배열 크기만큼 
    // 모든 경우의 값을 뽑아 내야 한다.

    const answer = [];
    const getCombination = (array, cnt) => {

        const result = [];

        array = array.sort();

        if(cnt === 1) return array.map(elem => [elem]);

        array.forEach((fixed, index, origin) => {
            
            const restCombination = getCombination(origin.slice(index+1), cnt -1);
            const combination = restCombination.map(comb => [fixed, ...comb]);

            result.push(...combination)
        })

        return result;

    }

    const nSet = new Set;

    for (const cnt of course) {
        const nHm  = new Map;
        const result = []
        for (const order of orders) {

            // 각각의 주문에서 단품을 코스의 cnt(숫자값)만큼 구성해야하므로 
            // 문자열을 배열로 전환
            const array = Array.from(order);
            getCombination(array, cnt).map(comb => comb.sort().join(''))
            .forEach(combString => result.push(combString))

        
        }

        result.forEach(comb=>{
            nHm.set(comb, (nHm.get(comb) || 0 ) + 1);
        })

        // 최대값 구하기
        let maxCnt = 0;
        result.forEach(comb => {
            if(nHm.get(comb) >= maxCnt) {
                maxCnt = nHm.get(comb);
            }
        })
        // 최대값을 고려한 2인 이상 주문한 메뉴만 Set(중복제거)에 추가
        result.forEach(comb => {
            if(nHm.get(comb) ===maxCnt && nHm.get(comb) >=2) {
                nSet.add(comb);
            }
        })

    }
    // 최종값에 정렬하여 담음
    nSet.forEach(a=>answer.push(a))

    console.log(answer.sort());

    return answer
  
}
