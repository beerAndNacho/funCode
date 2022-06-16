//문자열 압축
function solution(s) {

    let answer = 0;

    let strNew = s;



    answer = compress(strNew);

    return answer;
}

function compress(s){

    let min = 1000;
    if(s.length === 1) return 1;
    
    // 최대 압축할 수 있는 값은 문자열의 딱 반 
    for(let i = 1; i <= s.length/2; i++) {
        
        let str1 = '';
        let str2 = '';
        let ans = '';
        let cnt = 1;
        for(let z = 0; z < s.length; z+=i) {

            if(z===0){
                str1 = s.slice(z, z+i);
            }else{
                str2 = s.slice(z, z+i);
                if(str1 === str2){
                    cnt++;
                    if(i+z === s.length) ans += `${cnt}${str1}`;
            }else{
                if(cnt > 1) {
                    ans += `${cnt}${str1}`
                }else{
                    ans += str1;
                }
                cnt = 1;
                if(str1.length > str2.length) {
                    ans += str2;
                }
                str1 = str2;
                if(i+z === s.length) ans += str2;
            }
        }
    }

    console.log(ans);
    return Math.min(ans.length , min);
    }

}

const s = "abcabcabcabcdededededede"
console.log(solution(s));