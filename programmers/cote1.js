const id_list = ["muzi", "frodo", "apeach", "neo"]
let report =["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]
const k = 2






let id_list_cnt = [];
let result = []
let result2 = []

id_list.forEach(() => {
    id_list_cnt.push(0)
})

report.forEach(elem => {

    if(!result.includes(elem)) {
        result.push(elem)
    }
})

result.forEach(elem => {

    const em = elem.split(' ');

    console.log(em);


    result2.push(em[1])
})

result.forEach(elem => {
   let elem2 = elem.split(' ');

   for(let i=0; i< id_list.length; i++ ){
       if(elem2[0] === id_list[i]) {


        let count = result2.filter(elem => elem2[1] === elem).length;

        if(count >= k) {
            id_list_cnt[i] = id_list_cnt[i] + 1;
        }

       }
   }

})

console.log(id_list_cnt);




