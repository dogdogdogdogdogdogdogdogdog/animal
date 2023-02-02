
function publishAnimal(){

    var testForm=document.getElementById("fileForm")
    var kind=document.getElementById("createkind").value
    var variety=document.getElementById("createVariety").value
    var sex=document.getElementById("createSex").value
    var body=document.getElementById("createBodyShape").value
    var color=document.getElementById("createColor").value
    var age=document.getElementById("createAge").value
    var  ligation=document.getElementById("createLigation").value
    var address=document.getElementById("createAddress").value
    var remark=document.getElementById("createRemark").value

    if (testForm.files.length==false||kind==""||variety==""||sex==""||body==""||color==""||age==""||ligation==""||address==""||remark=="") { alert('資料請確實填寫');  return}
    var fd=new FormData();
    fd.append("animalPhoto",testForm.files[0])
    fd.append("kind",kind)
    fd.append("variety",variety)
    fd.append("sex",sex)
    fd.append("body",body)
    fd.append("color",color)
    fd.append("age",age)
    fd.append("ligation",ligation)
    fd.append("address",address)
    fd.append("remark",remark)

    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load',function(){
        if(xhr.status==201){
        document.getElementById("fileForm").value=""
        document.getElementById("createkind").value=""
        document.getElementById("createVariety").value=""
        document.getElementById("createSex").value=""
        document.getElementById("createBodyShape").value=""
        document.getElementById("createColor").value=""
        document.getElementById("createAge").value=""
        document.getElementById("createLigation").value=""
        document.getElementById("createAddress").value=""
        document.getElementById("createRemark").value=""
        alert('領養資料刊登成功')}
    })
    xhr.open('post', 'http://localhost:8080/publish_animal/textData');

    xhr.send(fd);



}
