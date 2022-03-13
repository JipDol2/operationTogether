const addLoginEvent = () =>{
  const loginFormSubmit = document.getElementById("login-form-submit");
  loginFormSubmit.addEventListener("click");
};

const loginOperation = async (event) =>{
  event.preventDefault();
  const loginDto={
    id: document.getElementById("id").value,
    password: document.getElementById("password").value,
  }
  if(!checkValidation(loginDto))return;
  const header ={
    method: 'POST',
    body: JSON.stringify(loginDto),
  }
  const response = await fetchData("/api/login",header);
  //TODO: OperationService 에서 어떻게 값을 @build 를 사용해서 저장하는지 확인
};

const checkValiation = (dto) =>{
  return true;
};
addLoginEvent();