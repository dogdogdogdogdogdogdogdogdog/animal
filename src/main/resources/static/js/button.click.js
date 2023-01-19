function send(){



    var account = {
        email: document.getElementById("emailAjax").value,
        password: document.getElementById("passwordAjax").value,
        name: document.getElementById("nameAjax").value,
        tel: document.getElementById("telAjax").value
    }

    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){


    })
    xhr.open('post', 'http://localhost:8080/user/register', true);
    xhr.setRequestHeader('Content-type', 'application/json');
    var data = JSON.stringify(account);
    xhr.send(data);


}