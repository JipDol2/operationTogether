const addIndexEvent = () => {
    isLoginCheck();
    const logoutButton = document.getElementById("logout-button");
    const createButton = document.getElementById("create-button");

    logoutButton.addEventListener("click",logoutOperation);
    createButton.addEventListener("click",createOperation);
}

const isLoginCheck = () => {
    const logoutState = document.getElementById("logoutState");
    const loginState = document.getElementById("loginState");

    const token = sessionStorage.getItem("Authorization");
    if(token!==null){
        logoutState.style.display="none";
        loginState.style.display="block";
    }
}
const logoutOperation = (event) =>{
    event.preventDefault();
    sessionStorage.clear();
    location.href=location.origin+`/`;
};

const createOperation = async (event) => {
    event.preventDefault();
    location.href = location.origin+`/create`;
}
addIndexEvent();