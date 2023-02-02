function judgeRegistEmail(email){

    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){
        if(xhr.status==200){
            document.getElementById("promptEmail").innerHTML="<div style='color: red'>Registed</div>"
        }
        if(xhr.status==404){
            document.getElementById("promptEmail").innerHTML="<div style='color: green'>Can use</div>"
        }
    })
xhr.open('get',"http://localhost:8080/user/registed?email="+document.getElementById(email).value);
xhr.send();
}
