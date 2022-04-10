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
    const operationSaveDto = {
        id: sessionStorage.getItem("Id")
    };
    const header={
        method: 'POST',
        body: JSON.stringify(operationSaveDto)
    }
    const response = await fetchData("/api/operations",header);
    const link = response.link;
    location.href = location.origin + `/operations/${link}`;
};

addIndexEvent();