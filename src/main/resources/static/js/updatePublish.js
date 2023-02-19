function updatePublish() {

    var infoModal = document.getElementById("infoModal");
    var inp = infoModal.getElementsByTagName('input')
    var fd = new FormData();
    let c = 0
    for (let i = 0; i < inp.length; i++) {
        if (inp[i].value != "") {
            if (inp[i].id == "animalPhoto") {
                fd.append(inp[i].id, inp[i].files[0])
            }
            fd.append(inp[i].id, inp[i].value)
            c++
        }
    }
    if (c == 0) {
        alert('請輸入修改值')
        return
    }
    fd.append("userId", user.id)
    fd.append("item", document.getElementById("infoModal").className)
    fd.append("imageUrl", '/static/images/publish/')


    var xhr = new XMLHttpRequest()
    xhr.addEventListener("load", function () {


        if (xhr.status == 200) {
            for (let i = 0; i < inp.length; i++) {
                inp[i].value == ""
            }
            alert('修改成功')
            window.location.reload();
        }

    })
    xhr.open('put', '/personalAnimals', true);
    xhr.send(fd);


}