// 시저 암호
const s = "ABz aaef H";
const n = 25;
// 65~90 A~Z
// 97~122 a~z
function solution(s, n) {
    let answer = '';

    [...s].map(elem => {

        if(elem ===' '){
            answer += elem
        }
       
      let ascii = elem.charCodeAt(0);

      //대문자
      if(ascii >= 65 && ascii <= 90) {

        if(ascii+n >90) {
            const range1 = ascii+n-90+65-1
            const a = String.fromCharCode(range1);
            answer += a
            console.log(elem,a);

        } else {
            const b = String.fromCharCode(ascii+n);
            answer += b
            console.log(elem,b);
        }
      }

      // 소문자
      if(ascii >= 97) {
        if(ascii+n > 122) {
            const range1 = ascii+n-122+97-1
            const a = String.fromCharCode(range1)
            answer += a
            console.log(elem,a);

        } else {
            const b = String.fromCharCode(ascii+n);
            answer += b
            console.log(elem,b);
        }

      }
    })
    return answer;
}

console.log((solution(s, n)));