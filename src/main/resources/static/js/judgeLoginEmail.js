function judgeLoginEmail(email){

    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){
        if(xhr.status==200){
            document.getElementById("loginPromptEmail").innerHTML=""
        }
        if(xhr.status==404){
            document.getElementById("loginPromptEmail").innerHTML="<div style='color: red'>尚未註冊請至註冊欄位註冊</div>"
        }
    })
    xhr.open('get',"http://localhost:8080/user/registed?email="+document.getElementById(email).value);
    xhr.send();
}