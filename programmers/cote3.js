


function solution(new_id) {
    
    // 대소문자 변경
    const step1 = new_id.toLowerCase();
    
    const step1Arr = step1.split('');
    
    const allowChar = ['1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','m','n','x','y','z','-','_','.'];
    
    // 소문자, 숫자 ,- _ . 를 제외한 모든 문자 제거
    const step2 = step1Arr.filter(char => {
    
        const charArr =  allowChar.filter(elem => {
            if(char === elem) {
                return char
            }
        })
        
        return charArr.toString();
    })
    
    // .. 연속 마침표 제거
    step3 = removeAgainDot(step2);
    
    let step4 = step3;
    // 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
    if(step4[0] === '.') {
        step4.splice(0, 1);
    }
    
    if(step4[step4.length-1] === '.') {
        step4.splice(step4.length-1, 1);
    }
    // 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
    let step5 = step4
    // !!! !!! [ 주의!!!!        그냥 a 하나만 대입하는 것이었음 이것 때문에 오류 났었습니다. 지문을 잘 읽자 예시만 의존하다가 헷갈렸음!!! 주의!!!!!!] !!!!
    // 값이없으면 그냥 a하나만 대입
    if(step5.length <= 0) {
        step5.push('a');
    }
    // 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
    // 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
    let step6 = step5;
    
    if(step6.length >= 16) {
        step6 = step6.slice(0, 15);
        if(step6[step6.length-1] === '.') {
            step6.splice(step6.length-1, 1);
        }
    }

    // 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
    let step7 = step6;

    while(true) {
        if(step7.length>=3) {
            break;
        }
        if(step7.length <=2) {
            step7.push(step7[step7.length-1])
        }
    }
    
    const answer = step7.join('');

    return answer;
    
    }
        
    function removeAgainDot (step2Arr) {
    
        for(let i=0; i < step2Arr.length; i++) {
            if(step2Arr[i] === '.' && step2Arr[i+1] ==='.') {
                step2Arr.splice(i, 1, 'rm');
            }
        }
    
        const filtered = step2Arr.filter(a => a !== 'rm');
    
        return filtered
    }
    

    const param = "...!@BaT#*..y.abcdefghijklm"
    solution(param)