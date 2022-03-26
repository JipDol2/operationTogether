const addLoginEvent = () =>{
  const loginFormSubmit = document.getElementById("login-form-submit");
  loginFormSubmit.addEventListener("click",loginOperation);
};

const loginOperation = async (event) =>{
  event.preventDefault();
  const loginDto={
    userId: document.getElementById("id").value,
    password: document.getElementById("password").value,
  }
  //if(!checkValidation(loginDto))return;
  const header ={
    method: 'POST',
    body: JSON.stringify(loginDto),
  }
  try{
    const response = await fetchData("/api/member/login",header);
    const token = response.token;
    if(token){
      sessionStorage.setItem("Authorization",`Barear ${token}`);
      location.href = location.origin+`/home`;
    }
  }catch(e){
    alert(e);
    location.reload();
  }
};
addLoginEvent();