 var user;
function checkLogin(){
  var  xhr1 = new XMLHttpRequest();
  var url=location.pathname.replaceAll("/","")
    xhr1.addEventListener('load',function(){
        console.log(xhr1.status)
        if(xhr1.status==200){


            user= JSON.parse(xhr1.responseText)

            document.getElementById("loginAndRegist").innerHTML=`<a href="javascript: goLoginAndRegist()"  class="nav-link">${user.email}<br>登出</a>`
            document.getElementById("userManagement").innerHTML='<a href="http://localhost:8080/user_management"  class="nav-link">會員中心</a>'
            document.getElementById("userPublishHistory").innerHTML='<a href="http://localhost:8080/user_publish_history"  class="nav-link">會員刊登資訊</a>'


            switch(url) {
                case 'user_management':
                    document.getElementById("userEmail").innerText=user.email
                    document.getElementById("userName").innerText=user.name
                    document.getElementById("userTel").innerText=user.tel
                    return
                case 'user_publish_history':
                 var   xhr = new XMLHttpRequest();

                    xhr.addEventListener("load", function () {


                        var  publishData= JSON.parse(xhr.responseText)
                       var publishContent= document.getElementById("publishContent")


                        for(let i=0;i<publishData.length;i++){
                            var tr = document.createElement('tr');
                            // tr.setAttribute('id',`item${publishData[i].item}`)
                            var td = document.createElement('td');
                            const item = document.createTextNode(publishData[i].item);
                            td.appendChild(item)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const kind = document.createTextNode(publishData[i].kind);
                            td.appendChild(kind)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const variety = document.createTextNode(publishData[i].variety);
                            td.appendChild(variety)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const sex = document.createTextNode(publishData[i].sex);
                            td.appendChild(sex)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const bodyShape = document.createTextNode(publishData[i].bodyShape);
                            td.appendChild(bodyShape)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const color = document.createTextNode(publishData[i].color);
                            td.appendChild(color)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const age = document.createTextNode(publishData[i].age);
                            td.appendChild(age)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const ligation = document.createTextNode(publishData[i].ligation);
                            td.appendChild(ligation)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const address = document.createTextNode(publishData[i].address);
                            td.appendChild(address)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const remark = document.createTextNode(publishData[i].remark);
                            td.appendChild(remark)
                            tr.appendChild(td)
                            var td = document.createElement('td');

                            var btn=document.createElement('button')
                            const update = document.createTextNode('更新');
                            btn.appendChild(update)
                            btn.onclick=function (){
                                infoModal=document.querySelector("#infoModal");
                                infoModal.showModal();
                                infoModal.setAttribute('class',publishData[i].item)
                            }
                            td.appendChild(btn)
                            var btn=document.createElement('button')
                            const del = document.createTextNode('刪除');
                            btn.onclick=function (){
                                delinfoModal = document.querySelector("#delinfoModal")
                                delinfoModal.showModal()
                                delinfoModal.setAttribute('class',publishData[i].item)
                            }
                            btn.appendChild(del)
                            td.appendChild(btn)

                            tr.appendChild(td)

                            publishContent.appendChild(tr)

                        }

                        return
                    })
                    xhr.open('get', "http://localhost:8080/publish_animals/" + user.id);
                    xhr.send();

            }
        }

    })
    xhr1.open('get',"http://localhost:8080/session-userId");
    xhr1.send();
}
function goLoginAndRegist(){
    xhr = new XMLHttpRequest();

    xhr.addEventListener('load',function(){

        if(xhr.status=200){
            window.location.replace("http://localhost:8080/login_register");
        }
})
xhr.open('get',"http://localhost:8080/sign_out")
xhr.send();
}
checkLogin()

