function regist(){
   var emailAjax= document.getElementById("emailAjax").value
   var passwordAjax = document.getElementById("passwordAjax").value
   var nameAjax= document.getElementById("nameAjax").value
    var telAjax=document.getElementById("telAjax").value
    var genderAjax=document.getElementById("genderAjax").value
    if(emailAjax=="" || passwordAjax=="" || nameAjax=="" || telAjax==""|| genderAjax==""){
        alert("註冊所有資料必須填寫")
        return
    }


    var account = {
        email: emailAjax,
        password: passwordAjax,
        name: nameAjax,
        tel: telAjax,
        gender:genderAjax
    }
    console.log(account.gender)
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){

        if (xhr.status==201){
           document.getElementById("emailAjax").value=""
            document.getElementById("passwordAjax").value=""
            document.getElementById("nameAjax").value=""
            document.getElementById("telAjax").value=""
            document.getElementById("promptEmail").innerText=""
            document.getElementById("genderAjax").value=""

            alert("註冊成功請登入")

            // document.getElementById("registStatus").innerHTML="<div style='color: green'>註冊成功請登入";
        }else {
            alert("註冊失敗")
            // document.getElementById("registStatus").innerHTML="<div style='color: red'>註冊失敗";
        }

    })
    xhr.open('post', 'http://localhost:8080/user/register', true);
    xhr.setRequestHeader('Content-type', 'application/json');
    var data = JSON.stringify(account);
    xhr.send(data);
}





