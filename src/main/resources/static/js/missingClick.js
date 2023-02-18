//刊登走失
function publishMissingAnimal() {
    // let missingInput = document.getElementById("missingInput")
    // let input = missingInput.getElementsByTagName("input")
    // console.log(input)
    // let fd = new FormData()
    // let c = 0
    // for (let i = 0; i < input.length; i++) {
    //     if (input[i].value != "") {
    //         c++
    //
    //         if (input[i].id == "animalPhoto") {
    //             fd.append(input[i].id, input[i].files[0])
    //         }
    //         fd.append(input[i].id, input[i].value)
    //     }
    // }
    // fd.append("imageUrl", '/static/images/publish/')
    // fd.append("userId", user.id)
    // if (c < (input.length)) {
    //     alert('資料請確實填寫');
    //     return
    // }
    let kind = document.getElementById("kind").value;
    let variety = document.getElementById("variety").value;
    let name = document.getElementById("name").value;
    let sex = document.getElementById("sex").value;
    let body = document.getElementById("bodysize").value;
    let color = document.getElementById("color").value;
    let age = document.getElementById("age").value;
    let area = document.getElementById("area").value;
    let missingDate = document.getElementById("missingDate").value;
    let description = document.getElementById("description").value;
    let file = document.getElementById("animalPhoto").files[0];
    let fd = new FormData();
    if (kind == "default" || sex == "default" || body == "default" || age == "default" ||
        variety == null || name == null || color == null || area == null || missingDate == null || description == null) {
        alert("請填寫所有欄位!");
        return
    } else if (file == null) {
        alert("請上傳照片!");
    }
    fd.append("userId", user.id);
    fd.append("kind", kind);
    fd.append("variety", variety);
    fd.append("name", name);
    fd.append("sex", sex);
    fd.append("bodysize", body);
    fd.append("color", color);
    fd.append("age", age);
    fd.append("area", area);
    fd.append("missingDate", missingDate);
    fd.append("description", description);
    fd.append("animalPhoto", file);
    fd.append("imageUrl", '/static/images/publish/')

    let xhr = new XMLHttpRequest();
    xhr.addEventListener('load', function () {
        if (xhr.status == 201) {
            if (confirm("刊登成功!，要繼續刊登嗎?") == true) {
                window.location.replace("/missing");
            } else {
                window.location.replace("/personal_shelter");
            }
        }
    })
    xhr.open('post', '/animalsMissing');
    xhr.send(fd);


}