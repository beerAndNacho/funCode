// 오픈채팅방

const record = ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Enter uid1234 Prodo","Change uid4567 Ryan", "Leave uid1234"];
console.log(solution(record));

function solution(record) {

    const id_NickName_matching = new Map();

    const recordUpdate = record.map(elem => {

        let elemSplit = elem.split(' ');
        
        if(elemSplit[0] !== 'Leave') {
            id_NickName_matching.set(elemSplit[1], elemSplit[2]);
        }
        
        return elemSplit;

    })

    console.log('recordUpdate', recordUpdate);
    // id에 최종 nickName 부여(나갔다 오든 변경하든 id 하나에 최종 nickName 값 부여)
    console.log('id_NickName_matching', id_NickName_matching);

    return checkState(recordUpdate, id_NickName_matching).filter(a=>a!==false)

}

function checkState (recordUpdate, id_NickName_matching) {


    return recordUpdate.map(elemSplit => {

        const state     = elemSplit[0];
        const id        = elemSplit[1];
        let nickName    = elemSplit[2];

        console.log('id_NickName_matching', id_NickName_matching);
        if(id_NickName_matching.get(id)) {
            nickName = id_NickName_matching.get(id);
        }
        let enterOrExit = '';

        if(state === 'Enter') {
            enterOrExit ='들어왔습니다.'
        }  
    
        if(state === 'Leave') {
            enterOrExit ='나갔습니다.'
        }
        
        if(state === 'Change') {
            return false;
        }

        return `${nickName}님이 ${enterOrExit}`;
    
        console.log(`${nickName}님이 ${enterOrExit}`);

    })
         
      

 

  



    
}