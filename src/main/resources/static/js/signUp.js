const addSignEvent= () => {
    const signFormSubmit = document.getElementById("signUp-form-submit");
    signFormSubmit.addEventListener("click",createSign);
}
const createSign = async (event) => {
    event.preventDefault();
    const SignDto ={
        name: document.getElementById("name").value,
        userId: document.getElementById("id").value,
        password: document.getElementById("password").value,
    };
    //checkValidation
    const header = {
        method: 'POST',
        body: JSON.stringify(SignDto)
    }
    const response = await fetchData("/api/member/signUp",header);
    if(JSON.stringify(response)==='{}'){
        return location.href = location.origin+`/login`;
    }
}
addSignEvent();