const addEvent = () => {
    document.getElementById('operation-date').value = new Date().toISOString().slice(0, 10);

    const operationFormSubmit = document.querySelector('#operation-form-submit');
    operationFormSubmit.addEventListener('click', createOperation);
}

const createOperation = async (event) => {
    event.preventDefault();
    const operationSaveDto = {
        name: document.getElementById('name').value,
        password: document.getElementById('password').value,
        type: document.getElementById('operation-type').value,
        operationDate: document.getElementById('operation-date').value,
    };
    if (!checkValidation(operationSaveDto)) return;
    const header = {
        method: 'POST',
        body: JSON.stringify(operationSaveDto)
    }
    const response = await fetchData("/api/operations", header);
    const link = response.link;
    window.location.href = "/operations/" + link;
}

const checkValidation = (dto) => {
    const errorMessage = document.getElementById('error-message');
    if (!isValidName(dto.name)) errorMessage.innerHTML = '유효하지 않은 이름입니다. (이름: 1~8자)';
    else if (!isValidPassword(dto.password)) errorMessage.innerHTML = '유효하지 않은 비밀번호입니다. (비밀번호: 4~12자)';
    else if (!isValidType(dto.type)) errorMessage.innerHTML = '작전 타입이 유효하지 않습니다.';
    else if (!isValidDate(dto.operationDate)) errorMessage.innerHTML = '작전 날짜가 유효하지 않습니다.';
    else {
        errorMessage.innerHTML = '';
        return true;
    }
}

const isValidName = (name) => {
    if (!name || name[0] === ' ') return false;
    if (name.length < 1 || name.length > 8) return false;
    return true;
};

const isValidPassword = (password) => {
    if (!password || password.length < 4 || password.length > 12) return false;
    if (/[^\w]/.test(password)) return false;
    return true;
};

const isValidType = (type) => {
    if (!/[\d]+/.test(type)) return false;
    return true;
}

const isValidDate = (date) => {
    if (!date) return false;
    const current = new Date().toISOString().slice(0, 10);
    if (new Date(date) < new Date(current)) return false;
    return true;
}

addEvent();