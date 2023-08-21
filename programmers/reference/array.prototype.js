const array1 = [1, 2, 3, 4];

const initialValue = 0;
const sumWithInitial = array1.reduce((accumulator, currentValue, idx) => accumulator + currentValue, initialValue);

console.log(sumWithInitial);

const picks = [1, 3, 2]
const checkReduce = picks.reduce((acc, cur) => acc + cur) * 5

console.log('checkReduce', checkReduce);


console.log('------------------------------------------------------------');

//slice() 메서드는 어떤 배열의 begin부터 end까지에 대한 얕은 복사본을 새로운 배열 객체로 반환한다.
// 원본 배열은 바뀌지 않는다.

const animals = ['ant', 'bison', 'camel', 'duck', 'elephant'];

console.log(animals.slice(2));

console.log(animals.slice(2, 4));



console.log('------------------------------------------------------------');

const array= [5, 12, 8, 130, 44,66];
let index = -1;
console.log(`Using an index of ${index} the item returned is ${array.at(index)}`);


