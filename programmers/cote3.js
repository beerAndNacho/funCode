let new_id = 	"1=...!@BaT#*..y.abcdefghijklm"

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

function removeAgainDot (step2) {

    let step2Arr = step2.split('');

    for(let i=0; i < step2Arr.length; i++) {
        if(step2Arr[i] === '.') {
            if(step2Arr[i+1] ==='.') {
                step2Arr.splice(i, 1, 'rm');
                console.log('step2Arr1', step2Arr);
            }
        }
    }

    console.log('step2Arr', step2Arr);

    const filtered = step2Arr.filter(a => a !== 'rm');

    return filtered
}


step3 = removeAgainDot(step2);

console.log(step3);

if(step3[0] === '.') {
    step3.splice(0, 1);
}

if(step3[step3.length-1] === '.') {
    step3.splice(step3.length-1, 1);
}


if(step3.length <= 0) {
    for(let i=0; i<new_id.length; i++) {
        step3.push('a');
    }
}

console.log(step3);

if(step3.length >= 16) {
    step3 = step3.slice(0, 15);

    if(step3[step3.length-1] === '.') {
        step3.splice(step3.length-1, 1);
    }
}

console.log(step3);
while(true) {
    if(step3.length>=3) {
        break;
    }
    if(step3.length <=2) {
        step3.push(step3[step3.length-1])
    }

}
console.log(step3);

const answer = step3.join('');


// step2.filter(elem => {
//     if(elem === '.') {

//     }
// })
