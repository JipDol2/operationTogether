const addLoginEvent = () =>{
  checkToken();
  const loginFormSubmit = document.getElementById("login-form-submit");

  loginFormSubmit.addEventListener("click",loginOperation);
};

const checkToken = () => {
  if(sessionStorage.getItem("Authorization")!=null){
    location.href = location.origin+`/`;
  }
}

const loginOperation = async (event) =>{
  event.preventDefault();
  const loginDto={
    userId: document.getElementById("id").value,
    password: document.getElementById("password").value,
  }
  const header ={
    method: 'POST',
    body: JSON.stringify(loginDto),
  }
  try{
    const response = await fetchData("/api/member/login",header);
    const token = response.token;
    if(token){
      sessionStorage.setItem("Authorization",`Bearer ${token}`);
      location.href = location.origin+'/';
    }
  }catch(e){
    const passwordInput = document.getElementById("password");
    passwordInput.value = null;
    passwordInput.focus();

    const errorMessage = document.getElementById('error-message');
    errorMessage.innerHTML="아이디 또는 비밀번호를 잘못 입력했습니다.\n입력하신 내용을 다시 확인해주세요."
    //location.reload();
  }
};
addLoginEvent();