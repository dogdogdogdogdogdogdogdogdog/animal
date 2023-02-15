//刊登走失
function publishMissingAnimal(){
    let missingInput = document.getElementById("missingInput")
    let input=missingInput.getElementsByTagName("input")
    let fd=new FormData
    let c=0
    for(let i=0;i<input.length;i++){
        if(input[i].value!="") {
            c++
            if (input[i].id == "missingAnimalPhoto") {
                fd.append(input[i].id, input[i].files[0])
            }
            fd.append(input[i].id, input[i].value)
        }
        }
    fd.append("photoUrl",'/static/images/publish/')
    fd.append("userId",user.id)
    if(c<(input.length)){alert('資料請確實填寫');return}

    let xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){
        if(xhr.status==201) {
            for (let i = 0; i < input.length; i++) {
                input[i].value = ""
            }
            alert('協尋資料刊登成功')
        }
    })
    xhr.open('post', 'http://localhost:8080/animalsMissing');

    xhr.send(fd);




 }