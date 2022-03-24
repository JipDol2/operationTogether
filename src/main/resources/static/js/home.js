const addHomeEvent = () =>{
    //1. 로그아웃 버튼을 클릭
    //2. 작전 만들기 버튼을 클릭
    const logoutButton = document.getElementById("logout-form-submit");
    const createButton = document.getElementById("create-form-submit");

    logoutButton.addEventListener("click",logoutOperation);
};

const logoutOperation = (event) =>{
    event.preventDefault();
    sessionStorage.clear();
    location.href=location.origin+`/login`;
};
addHomeEvent();