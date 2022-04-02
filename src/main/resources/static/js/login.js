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
    alert(e);
    location.reload();
  }
};
addLoginEvent();