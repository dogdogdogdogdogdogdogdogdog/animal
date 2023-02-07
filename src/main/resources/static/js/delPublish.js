function delPublish(){
var xhr =new XMLHttpRequest();



xhr.addEventListener("load", function (){
if(xhr.status==200){

    alert("刪除成功")
    window.location.reload();

}



})


    xhr.open("delete",`http://localhost:8080/personalAnimals/${user.id}/${document.getElementById("delinfoModal").className}`)
xhr.send()

}