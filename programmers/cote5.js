//문자열 압축
function solution(s) {

    var answer = 0;
    
    // let str ='';
    // let cnt ='';

    // for(let i =0; i<s.length; i++){

    //     if(i===0) {
    //         i = 1;
    //         ii = i*2;
    //     }else {
    //         ii = i*2
    //     }
    //     const strSlice = s.slice(0,(i));

    //     // console.log('i는->',i, strSlice);
    //     // console.log('i는',i,'ii는',ii,'->  ',s.slice(i,ii));

    //     // if(strSlice ==='abc') {
    //     // }

    //     if(strSlice === s.slice(i,ii)){
    //         // console.log('i는',i, 'ii는',ii);

    //         let diff = ii - i;

    //         for(let z=0; z<s.length; z+=diff) {

    //             let zz = z+diff;

    //             if(strSlice === s.slice(z, zz)) {

    //                 console.log(s.slice(z, zz));

    //             } else {
    //               break;
    //             }
    //         }

    //     }
    //  }


 
    if(s.length === 1) return 1;
    let min = 1000;
    for (let i =1; i <= s.length / 2; i++) {
        let str1 = '';
        let str2 = '';
        let ans = '';
        let count = 1;
        for (let j =0; j< s.length; j += i) {
            if (j === 0) {
                str1 = s.slice(j, j + i);
            }else{
                str2 = s.slice(j, j+i)
                if(str1 === str2){
                    count++;
                    if(j+i === s.length) ans += `${count}${str1}`;
                }else{
                    if(count > 1){
                        ans += `${count}${str1}`
                    }else{
                        ans += str1;
                    }
                    count = 1;
                    if(str1.length > str2.length) {
                        ans += str2;
                    }
                    str1 = str2;
                    if (j+i === s.length) ans += str2;
                }
            }

        }
        min = Math.min(ans.length, min);
    }

    return min;
}

const s = "abcabcabcabcdededededede"
console.log(solution(s));