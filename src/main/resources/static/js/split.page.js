
function search() {

var kind = document.getElementById("kind").value;
var shelter = document.getElementById("shelter").value;



    var x = document.getElementById("page").value;
    var request = new XMLHttpRequest();

    ajaxToServer(request,"select",2,"http://localhost:8080/public_shelter?page="+x+"&animalKind="+kind+"&shelterName="+shelter,"page")


   ajaxToServer(request,"section",1,"http://localhost:8080/public_shelter?page="+x+"&animalKind="+kind+"&shelterName="+shelter,"animalContext")

}


function sendPageToServer() {
    var x = document.getElementById("page").value;
    var request = new XMLHttpRequest();
    if(hasSearchCondition()){
        var kind = document.getElementById("kind").value;
        var shelter = document.getElementById("shelter").value;
        ajaxToServer(request,"section",1,"http://localhost:8080/public_shelter?page="+x+"&animalKind="+kind+"&shelterName="+shelter,"animalContext")

    }else {
        ajaxToServer(request,"section",1,"http://localhost:8080/public_shelter?page="+x,"animalContext")
    }
}
function sendPageToServerBySearch(){
    var kind = document.getElementById("kind").value;
    var shelter = document.getElementById("shelter").value;
    var x = document.getElementById("page").value;
    var request = new XMLHttpRequest();
    ajaxToServer(request,"section",1,"http://localhost:8080/public_shelter?page="+x+"&animalKind="+kind+"&shelterName="+shelter,"animalContext")
}
function goPrePage(){
    var x = document.getElementById("page").value;
    var p= parseInt(x, 10)-1;
    if(p==0){
        p=1
    }
    document.getElementById("page").value=p;
if(hasSearchCondition()){
    sendPageToServerBySearch()
}else {
    sendPageToServer()
}

}
function goNextPage(){
    var x = document.getElementById("page").value;
    var p= parseInt(x, 10)+1;
    var  pages=   document.getElementById("page").lastElementChild.value
    pages=parseInt(pages, 10)
    if(p>pages){
        p=pages
    }
    document.getElementById("page").value=p;

    if(hasSearchCondition()){
        sendPageToServerBySearch()
    }else {
        sendPageToServer()
    }
}


function stringToHtml(str,tagName,index){
    var parser = new DOMParser();
    var doc = parser.parseFromString(str, 'text/html');
    return doc.body.getElementsByTagName(tagName)[index].innerHTML;
}
function ajaxToServer(request,tagName,index,url,changeTag){
    var request = new XMLHttpRequest();
    request.addEventListener('load',function(){

        document.getElementById(changeTag).innerHTML =  stringToHtml(request.responseText,tagName,index)
    })
    request.open('get',url)
    request.send();
}
function hasSearchCondition(){
    var kind = document.getElementById("kind").value;
    var shelter = document.getElementById("shelter").value;
    if ((kind!="不分種類") || (shelter!="所有收容所")){
        return true
    }
    return false
}