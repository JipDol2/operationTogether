const addSignEvent= () => {
    const signFormSubmit = document.getElementById("signUp-form-submit");
    signFormSubmit.addEventListener("click",createSign);
}
const createSign = async (event) => {
    event.preventDefault();
    const signDto ={
        name: document.getElementById("name").value,
        userId: document.getElementById("id").value,
        password: document.getElementById("password").value,
    };
    if(!checkValidation(signDto))return;
    const header = {
        method: 'POST',
        body: JSON.stringify(signDto)
    }
    const response = await fetchData("/api/member/signUp",header);
    if(JSON.stringify(response)==='{}'){
        return location.href = location.origin+`/login`;
    }
}
const checkValidation = (dto) => {
    const errorMessage = document.getElementById('error-message');
    if (!isValidName(dto.name)) errorMessage.innerHTML = '유효하지 않은 이름입니다. (이름: 1~8자)';
    else if (!isValidPassword(dto.password)) errorMessage.innerHTML = '유효하지 않은 비밀번호입니다. (비밀번호: 4~12자)';
    else if (!isEqualPasswordCheck(dto.password, document.getElementById('password-check').value))
        errorMessage.innerHTML = '비밀번호와 비밀번호 확인이 일치하지 않습니다.';
    //else if (!isValidDate(dto.operationDate)) errorMessage.innerHTML = '작전 날짜가 유효하지 않습니다.';
    else {
        errorMessage.innerHTML = '';
        return true;
    }
};
const isValidName = (name) => {
    if (!name || name[0] === ' ') return false;
    if (name.length < 1 || name.length > 8) return false;
    return true;
};
const isValidPassword = (password) => {
    if (!password || password.length < 4 || password.length > 12) return false;
    /**^w : word 를 표현하며 알파벳 + 숫자 + _ 중의 한 문자임을 의미한다.*/
    if (/[^\w]/.test(password)) return false;
    return true;
};
const isEqualPasswordCheck = (password, passwordCheck) => {
    return password === passwordCheck;
}
addSignEvent();