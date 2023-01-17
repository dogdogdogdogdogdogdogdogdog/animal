function search() {

    var kind = document.getElementById("kind").value;
    var shelter = document.getElementById("shelter").value;
    var request = new XMLHttpRequest();
    ajaxToServer(request,"http://localhost:8080/public_shelter?page="+1+"&animalKind="+kind+"&shelterName="+shelter,[["page","select",2],["animalContext","section",1]])



}


function sendPageToServer() {
    var x = document.getElementById("page").value;
    var request = new XMLHttpRequest();
    if(hasSearchCondition()){
        var kind = document.getElementById("kind").value;
        var shelter = document.getElementById("shelter").value;
        ajaxToServer(request,"http://localhost:8080/public_shelter?page="+x+"&animalKind="+kind+"&shelterName="+shelter,[["animalContext","section",1]])

    }else {
        ajaxToServer(request,"http://localhost:8080/public_shelter?page="+x,[["animalContext","section",1]])
    }
}
function sendPageToServerBySearch(){
    var kind = document.getElementById("kind").value;
    var shelter = document.getElementById("shelter").value;
    var x = document.getElementById("page").value;
    var request = new XMLHttpRequest();
    ajaxToServer(request,"http://localhost:8080/public_shelter?page="+x+"&animalKind="+kind+"&shelterName="+shelter,[["animalContext","section",1]])
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

    console.log(doc.body.getElementsByTagName("animalContext"))
    return doc.body.getElementsByTagName(tagName)[index].innerHTML;
}
function ajaxToServer(request,url,changeList){

    request.addEventListener('load',function(){
        for(var i=0;i<changeList.length;i++){
           var changeTag=changeList[i][0]
           var tagName =changeList[i][1]
           var index=changeList[i][2]
            document.getElementById(changeTag).innerHTML =  stringToHtml(request.responseText,tagName,index)
        }
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



