function login(){
    var emailAjax= document.getElementById("loginEmail").value
    var passwordAjax = document.getElementById("loginPassword").value
    if(emailAjax=="" || passwordAjax=="" ){
        alert("登入所有資料必須填寫")
        return
    }
    var account = {
        email: emailAjax,
        password: passwordAjax,
    }
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){

        if (xhr.status==200){
            alert("登入成功")
            console.log(xhr.response)
            window.location.replace("http://localhost:8080/user_management");

            // document.getElementById("registStatus").innerHTML="<div style='color: green'>註冊成功請登入";
        }else if(xhr.status==400){
            alert("登入失敗 帳號或密碼輸入錯誤 ")
            // document.getElementById("registStatus").innerHTML="<div style='color: red'>註冊失敗";
        }

    })
    xhr.open('post', 'http://localhost:8080/user/login', true);
    xhr.setRequestHeader('Content-type', 'application/json');
    var data = JSON.stringify(account);
    xhr.send(data);
}