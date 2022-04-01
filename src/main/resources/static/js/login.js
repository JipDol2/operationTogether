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
      sessionStorage.setItem("Authorization",`Bearer ${token}`);
      //location.href = location.origin+'/home';
      const form = new FormData();
      form.append("Authorization",token);
      const res = navigator.sendBeacon(location.origin+`/home`,form);
      console.log(res);
    }
  }catch(e){
    alert(e);
    location.reload();
  }
};
addLoginEvent();