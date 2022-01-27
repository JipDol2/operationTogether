const addOperationEvent = () => {
    const modal = document.getElementById('result-modal');
    const input = document.getElementById('operation-password');
    modal.addEventListener('shown.bs.modal', () => {
        input.focus();
    });

    const inputButton = document.getElementById('btn-input');
    inputButton.addEventListener('click', redirectInput);

    const passwordButton = document.getElementById('btn-password');
    passwordButton.addEventListener('click', checkPassword);
}

const redirectInput = () => {
    location.href += '/input';
}

const checkPassword = async () => {
    const password = document.getElementById('operation-password').value;
    const passwordDto = {
        password,
    };
    const header = {
        method: 'POST',
        body: JSON.stringify(passwordDto),
    };
    const response = await fetchData("/api" + window.location.pathname, header);
    const token = response.token;
    if (token) {
        sessionStorage.setItem("Authorization", `Bearer ${token}`);
        location.href += '/result';
    } else {
        alert('비밀번호가 틀렸습니다.');
        location.reload();
    }
}

addOperationEvent();