const addSignEvent= () => {
    const signFormSubmit = document.getElementById("sign-form-submit");
    signFormSubmit.addEventListener("click",createSign);
}
const createSign = async (event) => {
    event.preventDefault();
    const SignDto ={
        name: document.getElementById("name").value,
        userId: document.getElementById("id").value,
        password: document.getElementById("password").value,
    };
    const header = {
        method: 'POST',
        body: JSON.stringify(SignDto)
    }
    const response = await fetchData("/api/sign",header);
    location.href = location.origin+`/login`;
}
addSignEvent();