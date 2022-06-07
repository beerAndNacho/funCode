let new_id = "1...!@BaT#*..y.abcdefghijklm";

new_id = new_id.toLowerCase();

const new_id_arr = new_id.split('');

const allowChar = ['1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','m','n','x','y','z','-','_','.'];


const step1 = new_id_arr.filter(char => {

const charArr =  allowChar.filter(a => {
       if(char === a) {
        return char
       }
   })
    return charArr.toString();
})

const step2 = step1.join('');

console.log(step2);

// function removeAgainDot () {

// }
// step2.filter(elem => {
//     if(elem === '.') {

//     }
// })
