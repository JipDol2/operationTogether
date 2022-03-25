const addHomeEvent = () =>{
    //1. 로그아웃 버튼을 클릭
    //2. 작전 만들기 버튼을 클릭
    const logoutButton = document.getElementById("logout-button");
    const createButton = document.getElementById("create-button");

    logoutButton.addEventListener("click",logoutOperation);
    createButton.addEventListener("click",createOperation);
};

const logoutOperation = (event) =>{
    event.preventDefault();
    sessionStorage.clear();
    location.href=location.origin+`/index`;
};

const createOperation = () => {
    location.href = location.origin+`/create`;
}
addHomeEvent();