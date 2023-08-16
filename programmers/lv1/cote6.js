const arr1 = [];

const number = 10000;
const arr = [];

for(let i = 1; i <= number; i++) {
    let sum = 0;

    const result = i.toString().split('');

    result.forEach(element => {
        sum += parseInt(element);
    });

    let result2 = sum+i;

    if(result2 <= number) {
        arr.push(result2);
    }
}

for(let i = 1; i <= number; i++) { 
    if(!arr.includes(i)) {
        console.log(i)
    }

}

// console.log(arr.sort((a,b)=>a-b));