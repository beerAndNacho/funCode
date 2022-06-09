//문자열 압축
function solution(s) {

    var answer = 0;
    
    let str ='';
    let cnt ='';

    for(let i =0; i<s.length; i++){

        const strSlice = s.slice(0,(i+1));
        let ii = i+i;
        if(i===0) {
            ii = 1;
        }
        // console.log(s.slice(i,ii));

        if(strSlice ==='abc') {
            console.log(s.slice(i,ii));
            console.log(strSlice);
        }
        // if(strSlice === s.slice(i,ii)){
        //     console.log(strSlice);
        // }
       
    }
    return answer;
}

const s = "abcabcabcabcdededededede";
solution(s);