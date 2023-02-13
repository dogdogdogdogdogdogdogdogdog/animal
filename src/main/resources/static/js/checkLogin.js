var user;
var status;

const url = location.pathname.replaceAll("/", "");

function init() {
    $.ajax({//取得登入session
        type: "GET",
        url: "/session-userId",
        async: false,
        success: function (data, text, xhr) {
            console.log("status:" + text + xhr.status);
            status = xhr.status;
            user = data;
            console.log(user);

            // 登入後顯示 {會員Email} 登出
            document.getElementById("login_stat").style.visibility = "visible";
            document.getElementById("login_stat").innerHTML = `${user.email} 登出`;
            document.getElementById("login_stat").href = "javascript: goLoginAndRegist()";
            // 登入後導覽列下拉選單隱藏「登入」連結
            document.getElementById("login").style.display = "none";
            // 登入後導覽列下拉選單顯示「登出」連結
            document.getElementById("sign_out").removeAttribute("style");
            // 登出連結導向goLoginAndRegist()
            document.getElementById("sign_out").href = "javascript: goLoginAndRegist()";
            // 登入後導覽列下拉選單顯示「會員資料變更」連結
            document.getElementById("userManagement").removeAttribute("style");
            // 登入後導覽列下拉選單顯示「會員刊登送養」連結
            document.getElementById("publish").removeAttribute("style");
            // 登入後導覽列下拉選單顯示「送養資訊管理」連結
            document.getElementById("userPublishHistory").removeAttribute("style");


            switch (url) {
                case 'user_management':
                    $("#userEmail").val(user.email);
                    $("#userName").val(user.name);
                    $("#userTel").val(user.tel);
                    return

                case 'user_publish_history':
                    $.ajax({//查詢該使用者的刊登資訊
                        type: "GET",
                        url: "/personalAnimals?id=" + user.id,
                        success: function (data) {
                            console.log(data);

                            $("#test1Result").empty();//清空元素
                            var title = `
                                <tr class='h5'>
                                    <th style='width: 10%;'>名字</th>
                                    <th style='width: 10%;'>物種</th>
                                    <th style='width: 10%;'>品種</th>
                                    <th style='width: 10%;'>性別</th>
                                    <th style='width: 10%;'>刊登日期</th>
                                    <th style='width: 10%;'>更新日期</th>
                                    <th style='width: 20%;'>操作</th>
                                </tr>`;

                            $("#test1Result").append(title);//加入標題

                            $.each(data["results"], function (index, item) {

                                var html = `
                                    <tr>
                                        <td>${item.animalName}</td>
                                        <td>${item.animalKind}</td>
                                        <td>${item.animalVariety}</td>
                                        <td>${item.animalSex}</td>
                                        <td>${item.createdDate}</td>
                                        <td>${item.lastModifiedDate}</td>
                                        <td>
                                            <button id="update" animalId="${item.animalId}" class="btn btn-primary" onclick="updateButton(this)">更新</button>
                                            <button id="delete" animalId="${item.animalId}" class="btn btn-primary" onclick="deleteButton(this)">刪除</button>
                                        </td>
                                    </tr>
                                `;
                                $("#test1Result").append(html);
                            })
                        }
                    })
            }
        },
        error: function (xhr, text) {
            console.log("status:" + text + xhr.status);
            user = null;
            console.log(user);


            if (xhr.status == 302 && (url == "user_publish_history" || url == "user_management" || url == "publish")) {
                window.location.href = '/login_register';
            }
        }
    });
}
init();

function goLoginAndRegist() {//??
    xhr = new XMLHttpRequest();

    xhr.addEventListener('load', function () {

        if (xhr.status = 200) {
            window.location.replace("http://localhost:8080/login_register");
        }
    })
    xhr.open('get', "http://localhost:8080/sign_out")
    xhr.send();
}

