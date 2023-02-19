function publishAnimal() {

    let kind = document.getElementById("createkind").value
    let variety = document.getElementById("createVariety").value
    let name = document.getElementById("createName").value
    let sex = document.getElementById("createSex").value
    let body = document.getElementById("createBodyShape").value
    let color = document.getElementById("createColor").value
    let age = document.getElementById("createAge").value
    let sterilization = document.getElementById("createLigation").value
    let bacterin = document.getElementById("createBacterin").value
    let area = document.getElementById("createAddress").value
    let description = document.getElementById("createRemark").value
    let file = document.getElementById("fileForm").files[0]
    let fd = new FormData();
    if (kind == "default" || sex == "default" || body == "default" || age == "default" || sterilization == "default" || area == "default" || name == null || variety == null || color == null || bacterin == null) {
        alert("請填寫所有欄位!");
        return
    } else if (file == null) {
        alert("請上傳照片!");
    }
    fd.append("animalPhoto", file)
    fd.append("userId", user.id)
    fd.append("animalName", name)
    fd.append("animalKind", kind)
    fd.append("animalVariety", variety)
    fd.append("animalSex", sex)
    fd.append("animalAge", age)
    fd.append("animalBodysize", body)
    fd.append("animalColor", color)
    fd.append("animalSterilization", sterilization)
    fd.append("animalBacterin", bacterin)
    fd.append("photoUrl", user.id)
    fd.append("area", area)
    fd.append("description", description)
    let xhr = new XMLHttpRequest()
    xhr.addEventListener("load", function () {
        if (xhr.status == 201) {
            if (confirm("刊登成功!，要繼續刊登嗎?") == true) {
                window.location.replace("/publish");
            } else {
                window.location.replace("/personal_shelter");
            }
        }
    })
    xhr.open('post', '/personalAnimals')
    xhr.send(fd)


}
