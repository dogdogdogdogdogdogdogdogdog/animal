var user;

function checkLogin_bak() {
    var xhr1 = new XMLHttpRequest();
    var url = location.pathname.replaceAll("/", "")
    xhr1.addEventListener('load', function () {
        console.log(xhr1.status)
        if (xhr1.status == 302 && (url == "user_publish_history" || url == "user_management" || url == "publish")) {
            window.location.href = '/login_register';
        }

        if (xhr1.status == 200) {


            user = JSON.parse(xhr1.responseText)

            // 登入後顯示 {會員Email} 登出
            document.getElementById("login_stat").style.visibility = "visible";
            document.getElementById("login_stat").innerHTML = `${user.email} 登出`;
            document.getElementById("login_stat").href = "javascript: goLoginAndRegist()";
            // 登入後導覽列下拉選單隱藏「登入」連結 | 滑鼠掠過時圓弧效果移除
            document.getElementById("login").removeAttribute("style");
            document.getElementById("login").style.display = "none";
            // 登入後導覽列下拉選單顯示「登出」連結
            document.getElementById("sign_out").removeAttribute("style");
            // 登出連結導向goLoginAndRegist()
            document.getElementById("sign_out").href = "javascript: goLoginAndRegist()";
            // 登入後導覽列下拉選單隱藏「會員資料變更」連結
            document.getElementById("userManagement").removeAttribute("style");
            // 登入後導覽列下拉選單隱藏「會員刊登送養」連結
            document.getElementById("publish").removeAttribute("style");
            // 登入後導覽列下拉選單隱藏「送養資訊管理」連結
            document.getElementById("userPublishHistory").removeAttribute("style");

            switch (url) {
                case 'user_management':
                    document.getElementById("userEmail").value = user.email;
                    document.getElementById("userName").value = user.name;
                    document.getElementById("userTel").value = user.tel;
                    return
                case 'user_publish_history':
                    var xhr = new XMLHttpRequest();

                    xhr.addEventListener("load", function () {


                        var publishData = JSON.parse(xhr.responseText)
                        var publishContent = document.getElementById("publishContent")
                        publishData = publishData.results

                        for (let i = 0; i < publishData.length; i++) {
                            var tr = document.createElement('tr');
                            // tr.setAttribute('id',`item${publishData[i].item}`)
                            var td = document.createElement('td');
                            const name = document.createTextNode(publishData[i].animalName);
                            td.appendChild(name)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const animalBacterin = document.createTextNode(publishData[i].animalBacterin);
                            td.appendChild(animalBacterin)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const kind = document.createTextNode(publishData[i].animalKind);
                            td.appendChild(kind)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const variety = document.createTextNode(publishData[i].animalVariety);
                            td.appendChild(variety)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const sex = document.createTextNode(publishData[i].animalSex);
                            td.appendChild(sex)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const bodyShape = document.createTextNode(publishData[i].animalBodysize);
                            td.appendChild(bodyShape)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const color = document.createTextNode(publishData[i].animalColor);
                            td.appendChild(color)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const age = document.createTextNode(publishData[i].animalAge);
                            td.appendChild(age)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const ligation = document.createTextNode(publishData[i].animalSterilization);
                            td.appendChild(ligation)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const address = document.createTextNode(publishData[i].area);
                            td.appendChild(address)
                            tr.appendChild(td)
                            var td = document.createElement('td');
                            const remark = document.createTextNode(publishData[i].description);
                            td.appendChild(remark)
                            tr.appendChild(td)
                            var td = document.createElement('td');

                            var btn = document.createElement('button')
                            const update = document.createTextNode('更新');
                            btn.appendChild(update)
                            btn.onclick = function () {
                                infoModal = document.querySelector("#infoModal");
                                infoModal.showModal();
                                infoModal.setAttribute('class', publishData[i].animalId)
                                document.getElementById("animalVariety").value = publishData[i].animalVariety
                                document.getElementById("animalKind").value = publishData[i].animalKind
                                document.getElementById("animalSex").value = publishData[i].animalSex
                                document.getElementById("animalBodysize").value = publishData[i].animalBodysize
                                document.getElementById("animalColor").value = publishData[i].animalColor
                                document.getElementById("animalAge").value = publishData[i].animalAge
                                document.getElementById("animalSterilization").value = publishData[i].animalSterilization
                                document.getElementById("area").value = publishData[i].area
                                document.getElementById("description").value = publishData[i].description
                                document.getElementById("animalBacterin").value = publishData[i].animalBacterin
                                document.getElementById("animalName").value = publishData[i].animalName
                                document.getElementById("curImg").innerHTML = `<img src="images/publish/${publishData[i].userId}-${publishData[i].animalId}.jpg"  width="300" height="400">`


                            }
                            td.appendChild(btn)
                            var btn = document.createElement('button')
                            const del = document.createTextNode('刪除');
                            btn.onclick = function () {
                                delinfoModal = document.querySelector("#delinfoModal")
                                delinfoModal.showModal()
                                delinfoModal.setAttribute('class', publishData[i].animalId)
                            }
                            btn.appendChild(del)
                            td.appendChild(btn)

                            tr.appendChild(td)

                            publishContent.appendChild(tr)

                        }

                        return
                    })
                    xhr.open('get', "http://localhost:8080/personalAnimals?id=" + user.id);
                    xhr.send();

            }
        }

    })
    xhr1.open('get', "http://localhost:8080/session-userId");
    xhr1.send();
}

function goLoginAndRegist() {
    xhr = new XMLHttpRequest();

    xhr.addEventListener('load', function () {

        if (xhr.status = 200) {
            window.location.replace("http://localhost:8080/login_register");
        }
    })
    xhr.open('get', "http://localhost:8080/sign_out")
    xhr.send();
}

checkLogin_bak()

