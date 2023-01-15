function sendPageToServer() {

    var stringToHTML = function (str) {
        var parser = new DOMParser();
        var doc = parser.parseFromString(str, 'text/html');
        return doc.body.getElementsByTagName('section')[1].innerHTML;
    };

    var x = document.getElementById("page").value;



    var request = new XMLHttpRequest();
    request.addEventListener('load',function(){
        console.log(stringToHTML(request.responseText))
        document.getElementById('animalContext').innerHTML =  stringToHTML(request.responseText)
    })
    request.open('get',"http://localhost:8080/public_shelter?page="+x)
    request.send();
}

function goPrePage(){
    var x = document.getElementById("page").value;
    var p= parseInt(x, 10)-1;
    if(p==0){
        p=1
    }
    document.getElementById("page").value=p;
    sendPageToServer()

}
function goNextPage(){
    var x = document.getElementById("page").value;
    var p= parseInt(x, 10)+1;
    if(p>545){
        p=545

    }
    document.getElementById("page").value=p;

    sendPageToServer()


}